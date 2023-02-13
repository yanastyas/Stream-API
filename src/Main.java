import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minorPeople = persons.stream()
                .filter(i -> i.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + minorPeople);

        List<String> conscriptList = persons.stream()
                .filter(i -> i.getSex() == Sex.MAN)
                .filter(s -> s.getAge() >= 18)
                .filter(g -> g.getAge() <= 27)
                .map(Person::getFamily).collect(Collectors.toList());
        System.out.println("Количество призывников: " + conscriptList);

        List<String> workList = persons.stream()
                .filter(i -> (i.getEducation() == Education.HIGHER))
                .filter(s -> (s.getAge() >= 18))
                .filter(g -> (g.getSex() == Sex.MAN && g.getAge() <= 65)
                        || (g.getSex() == Sex.WOMAN && g.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily).collect(Collectors.toList());
        System.out.println("Количество потенциально работоспособных людей с высшим образованием: " + workList);

    }
}