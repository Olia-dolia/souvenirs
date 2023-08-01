package liakholga.app.servise;

import liakholga.app.producers.Producer;
import liakholga.app.souvenirInterface.Souvenir;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Service {

    //method to read from file

    public List<String> getAllProducers(List<Souvenir> souvenirs) {
        return souvenirs.stream().map(x -> x.getProducer()
                .getName())
                .distinct()
                .toList();
    }

    public void getAllSouvenirs(List<Souvenir> souvenirs) {
        souvenirs.forEach(System.out::println);
    }

    public void terminalOutput(List<String> list) {
        list.forEach(System.out::println);
    }

    public void getSouvenirsByCountry(List<Souvenir> souvenirs, String country) {
        souvenirs.stream().filter(souvenir -> souvenir.getProducer().getCountry().equals(country))
                .forEach(System.out::println);
    }

    public void getProducerByPrice(List<Souvenir> souvenirs, double price) {
        souvenirs.stream().filter(x -> x.getPrice() < price)
                .forEach(producer -> System.out.printf("Producer - %s, country - \"%s\" \n",
                        producer.getProducer().getName(), producer.getProducer().getCountry()));
    }

    public Souvenir updateSouvenir(List<Souvenir> souvenirs, String name, Producer producer) {
        int index = getIndex(souvenirs, name, producer);
        Souvenir souvenir = souvenirs.get(index);
        //set new data
        String newName, newCountry, newDate;
        double newPrice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new name: ");
        newName = scanner.nextLine();
        System.out.println("Enter new Producer Country: ");
        newCountry = scanner.nextLine();
        producer.setCountry(newCountry);
        System.out.println("Enter new date(dd/MM/yyyy): ");
        newDate = scanner.nextLine();
        System.out.println("Enter new price: ");
        newPrice = scanner.nextDouble();

        return souvenir.setSouvenir(newName, producer, new Date(newDate), newPrice);
    }

    public int getIndex(List<Souvenir> souvenirs, String name, Producer producer) {
        //find S in list
        int index = 0;
        for (Souvenir s : souvenirs) {
            if (s.findByNameAndProducer(name, producer) != null) {
                //get index
                return souvenirs.indexOf(s);
            }
        }
        return index;
    }

    public Map<String, List<Souvenir>> getProducerAndItsSouvenirs(List<Souvenir> souvenirs) {
        return souvenirs.stream()
                .collect(Collectors.groupingBy(souvenir -> souvenir.getProducer().getName()));
    }

    public <T> void printMap(Map<T, List<Souvenir>> map, int choice) {
        for (Map.Entry<T, List<Souvenir>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            switch (choice) {
                case 1 -> {
                    for (Souvenir s : entry.getValue()) {
                        System.out.print(s.getSouvenir() + "\n");
                    }
                }
                case 2 -> {
                    for (Souvenir s : entry.getValue()) {
                        System.out.print(s.toString() + "\n");
                    }
                }
            }
        }
    }

    public void getProducerByYear(List<Souvenir> souvenirs, int year) {
        System.out.println("For product in " + year + " year");
        souvenirs.stream().filter(x -> (x.getDate().getYear() + 1900) == year)
                .forEach(x -> System.out.printf("Producer - %s, country - \"%s\" \n",
                        x.getProducer().getName(), x.getProducer().getCountry()));
    }

    public Map<Integer, List<Souvenir>> getSouvenirsByYear(List<Souvenir> souvenirs) {
        return souvenirs.stream()
                .collect(Collectors.groupingBy(souvenir -> souvenir.getDate().getYear()+1900));
    }

    public List<Souvenir> removeSouvenirByProducer(List<Souvenir> souvenirs, String producer) {
        souvenirs.removeIf(souvenir -> souvenir.getProducer().getName().equals(producer));
        return souvenirs.subList(0, souvenirs.size());
    }

    //method to write in file
}
