import java.io.*;

public class AccountManagerImpl implements AccountManager{

    @Override
    public void registerNewAccount(String email, String password, Person person) {
        String a;
        BufferedWriter fileOut = null;
        BufferedReader fileIn = null;
        try {
            fileIn = null;
            fileOut = null;
            String accounts = "";
            fileIn = new BufferedReader(new FileReader("C:/IdeaProjects/lesson4/accounts.csv"));
            while ((a = (fileIn.readLine())) != null) {
                String[] line = a.split(",");
                if (email.equals(line[0])) {
                    throw new DuplicateAccountException();
                }
                accounts += line[0] + "," + line[1] + "," + line[2] + "," + line[3] + "\n";
            }

            fileOut = new BufferedWriter(new FileWriter("C:/IdeaProjects/lesson4/accounts.csv"));
            fileOut.write(accounts + email + "," + password + "," + person.name + "," + person.birthDate);
        } catch (DuplicateAccountException e) {
            System.out.println("Exception: " + e.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
            if (fileOut != null) {
                try {
                    fileOut.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
        }

    }

    @Override
    public void removeAccount(String email, String password) {
        String a;
        BufferedWriter fileOut = null;
        BufferedReader fileIn = null;
        try {
            fileIn = null;
            fileOut = null;
            boolean checker = false;
            String accounts = "";
            fileIn = new BufferedReader(new FileReader("C:/IdeaProjects/lesson4/accounts.csv"));
            while ((a = (fileIn.readLine())) != null) {
                String[] line = a.split(",");
                if (email.equals(line[0]) && password.equals(line[1])) {
                    checker = true;
                }
                else {
                    accounts += line[0] + "," + line[1] + "," + line[2] + "," + line[3] + "\n";
                }
            }
            if (!checker) {
                throw new WrongCredentialsException();
            }

            fileOut = new BufferedWriter(new FileWriter("C:/IdeaProjects/lesson4/accounts.csv"));
            fileOut.write(accounts);
        } catch (WrongCredentialsException e) {
            System.out.println("Exception: " + e.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
            if (fileOut != null) {
                try {
                    fileOut.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    @Override
    public boolean hasAccount(String email) {
        String a;
        BufferedReader fileIn = null;
        boolean checker = false;
        try {
            fileIn = null;
            fileIn = new BufferedReader(new FileReader("C:/IdeaProjects/lesson4/accounts.csv"));
            while ((a = (fileIn.readLine())) != null) {
                String[] line = a.split(",");
                if (email.equals(line[0])) {
                    checker = true;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
        return checker;
    }

    @Override
    public Person getPerson(String email, String password) {
        String a;
        BufferedReader fileIn = null;
        int attempts;
        AttemptCounter attempt = AttemptCounter.getInstance();
        try {
            fileIn = null;
            fileIn = new BufferedReader(new FileReader("C:/IdeaProjects/lesson4/accounts.csv"));
            while ((a = (fileIn.readLine())) != null) {
                String[] line = a.split(",");
                if (email.equals(line[0]) && password.equals(line[1])) {
                    System.out.println(line[2] + ": " + line[3]);
                    return new Person(line[2], line[3]);
                }
                if (email.equals(line[0])) {
                    attempts = attempt.count(email);
                    System.out.println(attempts);
                    if (attempts > 5) {
                        throw new TooManyLoginAttemptsException();
                    }
                }
            }
            throw new WrongCredentialsException();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } catch (WrongCredentialsException e) {
            System.out.println("Exception: " + e.toString());
        } catch (TooManyLoginAttemptsException e) {
            System.out.println("Exception: " + e.toString());
        }
        finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
        return null;
    }

    @Override
    public int numOfAccounts() {
        BufferedReader fileIn = null;
        int counter = 0;
        try {
            fileIn = null;
            fileIn = new BufferedReader(new FileReader("C:/IdeaProjects/lesson4/accounts.csv"));
            while (fileIn.readLine() != null) {
                counter++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } finally {
            if (fileIn != null) {
                try {
                    fileIn.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
        return counter;
    }
}
