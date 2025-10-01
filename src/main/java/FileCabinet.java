import java.util.*;
import java.util.stream.Stream;

public class FileCabinet implements Cabinet {

    private final List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = Objects.requireNonNullElseGet(folders, ArrayList::new);
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        if (name == null) return Optional.empty();
        return traverse()
                .filter(f -> name.equals(f.getName()))
                .findFirst();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        if (size == null) return List.of();
        return traverse()
                .filter(f -> size.equals(f.getSize()))
                .toList();
    }

    @Override
    public int count() {
        return (int) traverse().count();
    }

    private Stream<Folder> traverse() {
        return folders.stream().flatMap(this::flatten);
    }

    private Stream<Folder> flatten(Folder folder) {
        if (folder instanceof MultiFolder mf) {
            return Stream.concat(
                    Stream.of(mf),
                    mf.getFolders().stream().flatMap(this::flatten)
            );
        }
        return Stream.of(folder);
    }
}