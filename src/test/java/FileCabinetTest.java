import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// przykładowe implementacje Folder do testów
class SimpleFolder implements Folder {
    private final String name;
    private final String size;

    SimpleFolder(String name, String size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getSize() { return size; }
}

class CompositeFolder implements MultiFolder {
    private final String name;
    private final String size;
    private final List<Folder> folders;

    CompositeFolder(String name, String size, List<Folder> folders) {
        this.name = name;
        this.size = size;
        this.folders = folders;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getSize() { return size; }

    @Override
    public List<Folder> getFolders() { return folders; }
}

public class FileCabinetTest {

    private FileCabinet cabinet;

    @BeforeEach
    void setUp() {
        Folder f1 = new SimpleFolder("docs", "SMALL");
        Folder f2 = new SimpleFolder("music", "MEDIUM");
        Folder f3 = new SimpleFolder("photos", "LARGE");
        Folder f4 = new SimpleFolder("archive", "SMALL");

        MultiFolder subFolder = new CompositeFolder("projects", "MEDIUM", List.of(f4));
        MultiFolder root = new CompositeFolder("root", "LARGE", List.of(f1, f2, f3, subFolder));

        cabinet = new FileCabinet(List.of(root));
    }

    @Test
    void testFindFolderByName_existing() {
        Optional<Folder> result = cabinet.findFolderByName("music");
        assertTrue(result.isPresent());
        assertEquals("music", result.get().getName());
    }

    @Test
    void testFindFolderByName_nonExisting() {
        Optional<Folder> result = cabinet.findFolderByName("unknown");
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindFolderByName_null() {
        Optional<Folder> result = cabinet.findFolderByName(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindFoldersBySize_small() {
        List<Folder> result = cabinet.findFoldersBySize("SMALL");
        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(f -> f.getSize().equals("SMALL")));
    }

    @Test
    void testFindFoldersBySize_large() {
        List<Folder> result = cabinet.findFoldersBySize("LARGE");
        assertEquals(2, result.size()); // root + photos
    }

    @Test
    void testFindFoldersBySize_null() {
        List<Folder> result = cabinet.findFoldersBySize(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testCount() {
        int count = cabinet.count();
        // root + docs + music + photos + projects + archive = 6
        assertEquals(6, count);
    }
}