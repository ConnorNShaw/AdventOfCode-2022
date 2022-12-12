import java.util.ArrayList;

public class Directory {

    //Point to its outer directory
    //Contains a block with everything in the directory

    private final Directory outerDirectory;
    private final ArrayList<Object> block;
    private final String letter;
    private int size = 0;

    public Directory(Directory outerDirectory, String letter) {
        this.outerDirectory = outerDirectory;
        this.letter = letter;
        block = new ArrayList<>();
    }

    public Directory getOuterDirectory() {
        return outerDirectory;
    }

    public Directory findDirectory(String d) {
        for (Object o: block) {
            if (o instanceof Directory && ((Directory) o).letter.equals(d)) {
                return (Directory) o;
            }
        }
        return null;
    }

    public void addItem(Object item) {
        block.add(item);
    }

    public int getSize() {
        if(size == 0) {
            calcSize(size);
        }
        return size;
    }

    private void calcSize(int size) {
        for (Object o : block) {
            if (o instanceof FileItem) {
                size += ((FileItem) o).getSize();
            }
            if (o instanceof Directory) {
                size += ((Directory) o).getSize();
            }
        }
        this.size = size;
    }
}