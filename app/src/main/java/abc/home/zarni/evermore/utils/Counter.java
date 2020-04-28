package abc.home.zarni.evermore.utils;

public class Counter {

    private int value;

    public Counter() {
        this.value = 0;
    }

    public Counter(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public void pulus(){
        this.value++;
    }

    public void minus(){
        this.value--;
    }

    public void reset(){
        this.value = 0;
    }

}
