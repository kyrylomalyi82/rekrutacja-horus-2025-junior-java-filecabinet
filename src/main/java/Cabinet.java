import java.util.*;

public interface Cabinet {
    Optional<Folder> findFolderByName(String name);
    List<Folder> findFoldersBySize(String size);
    int count();
}