~~~~
建造者模式:
    当类的属性过多时，使用构造方法特别冗余，这时使用builder模式

Class Desk {
    int height;
    int width;
    int length;

    public Desk () {}

    public Desk(int h, int w, int l) {
        height = h;
        width = w;
        length = l;
    }

    public static DeskBuilder build() {
        return new DeskBuilder(); 
    }
    
    static class DeskBuilder {
        int height;
        int width;
        int length;

        DeskBuilder height(int h) {
            this.height = h;
            return this;
        }
        
        ...

        Desk build() {
            return new Desk(this.height, this.width, this.length);
        }
    }
}
~~~~