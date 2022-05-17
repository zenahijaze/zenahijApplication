package hij.zena.zenahijapplication.MyData;

public class MyNote
{
    private String key;
    private String owner;

    private String title;
    private String text;
    private String pkgname;
   private boolean isimportant;
   private boolean isdeleteed;
    private boolean isnecessity;

    public boolean isIsdeleteed() {
        return isdeleteed;
    }

    public void setIsdeleteed(boolean isdeleteed) {
        this.isdeleteed = isdeleteed;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPkgname() {
        return pkgname;
    }

    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    public boolean isIsimportant() {
        return isimportant;
    }

    public void setIsimportant(boolean isimportant) {
        this.isimportant = isimportant;
    }

    public boolean isIsnecessity() {
        return isnecessity;
    }

    public void setIsnecessity(boolean isnecessity) {
        this.isnecessity = isnecessity;
    }
}
