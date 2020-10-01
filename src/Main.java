public class Main {
    public static void main (String[] args) {
        AccountManagerImpl acc = new AccountManagerImpl();
        Person pers = new Person("Bob", "01.01.2000");
        Person pers2 = new Person("Mike", "03.06.1999");
        acc.registerNewAccount("abc@gmail.com", "1234", pers);
        acc.registerNewAccount("abcd@mail.ru", "5473", pers2);
        acc.removeAccount("abcd@mail.ru", "5473");
        acc.removeAccount("abcd@mail.ru", "5471");
        System.out.println(acc.hasAccount("abc@gmail.com"));
        acc.getPerson("abcdef@gmail.com", "1234");
        acc.getPerson("abc@gmail.com", "123");
        acc.getPerson("abc@gmail.com", "123");
        acc.getPerson("abcdef@gmail.com", "123");
        acc.getPerson("abc@gmail.com", "123");
        acc.getPerson("abc@gmail.com", "123");
        acc.getPerson("abc@gmail.com", "123");
        acc.getPerson("abcdef@gmail.com", "123");
        acc.getPerson("abc@gmail.com", "123");
        System.out.println(acc.numOfAccounts());
    }
}
