package Entity;

public class Numbers {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Numbers() {
    }

    public Numbers(int number) {
        this.number = number;
    }
    public int increase(int i){
        this.setNumber(i);
        number+=number;
        return number;
    }
}
