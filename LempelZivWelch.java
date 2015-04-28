package lzw;

public class LempelZivWelch {
    protected String fileTextSource;
    protected String fileBinarySource;
    protected Dictionary tableWords;
    
    protected LempelZivWelch(String newFileTextSource, String newFileBinarySource) {
        if (newFileTextSource == null || newFileBinarySource == null) {
            System.out.println("ERROR: invalid path");
            System.exit(1);
        }
        
        /*TODO: check for correct file extension*/
        
        this.fileTextSource = newFileTextSource;
        this.fileBinarySource = newFileBinarySource;
        this.tableWords = new Dictionary();
    }
    
    public String getFileTextSource() {
        return fileTextSource;
    }

    public void setFileTextSource(String fileTextSource) {
        this.fileTextSource = fileTextSource;
    }

    public String getFileBinarySource() {
        return fileBinarySource;
    }

    public void setFileBinarySource(String fileBinarySource) {
        this.fileBinarySource = fileBinarySource;
    }
}
