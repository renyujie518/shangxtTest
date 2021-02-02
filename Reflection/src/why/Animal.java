package why;

public abstract  class Animal{

    //
    public String nickName;
    protected int age;

    //

    public Animal() {
    }

    public Animal(String nickName, int age) {
        this.nickName = nickName;
        this.age = age;
    }


    //

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void shout(){
        System.out.println("-----shout()----");
    }

    public void shout(String str){
        System.out.println("-----shout()----"+str);
    }

    public void shout(String str,int num){
        System.out.println("-----shout()----"+str+"  "+num);
    }

    public abstract  void eat();

    @Override
    public String toString() {
        return "Animal{" +
                "nickName='" + nickName + '\'' +
                ", age=" + age +
                '}';
    }
}
