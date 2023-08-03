package liakholga.app.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import liakholga.app.producers.*;
import liakholga.app.souvenirInterface.Souvenir;
import liakholga.app.souvenirs.KyivCityPostcard;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    private List<Path> getFilesName() throws IOException {
        return Files.list(Path.of("src/main/resources/")).toList();
    }

    public List<String[]> readFromFiles() throws Exception { //Path filePath
        List<Path> paths = getFilesName();
        List<String[]> list = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        for (Path p : paths) {
            Reader reader = Files.newBufferedReader(p);
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();

            try (reader) {
                try (csvReader) {
                    String[] line;
                    while ((line = csvReader.readNext()) != null) {
                        list.add(line);
                    }
                }
            }
        }
        return list;
    }

    public List<Souvenir> parseSouvenirInputList(List<String[]> fromFile) {
        List<Souvenir> souvenirs = new ArrayList<>();
        for (String[] str : fromFile) {
            souvenirs.add(parseProducer(str));
        }
        return souvenirs;
    }

    private Souvenir parseProducer(String[] s) {
        switch (s[0].toLowerCase()) {
            case "novapost" -> {
                return parseNovaPost(s);
            }
            case "ukrpost" -> {
                return parseUkrPost(s);
            }
            case "kyivcity" -> {
                return parseKyivCity(s);
            }
            case "privatbank" -> {
                return parsePrivatBank(s);
            }
        }
        return null;
    }

    private Souvenir parseNovaPost(String[] s) {
        String souvenirType = s[1].toLowerCase();
        if (souvenirType.contains("cup")) {
            return new NovaPost().createCup(s[1], new NovaPost(s[2]), new Date(s[3]), Double.parseDouble(s[4]));
        }
        if (souvenirType.contains("pen")) {
            return new NovaPost().createPen(s[1], new NovaPost(s[2]), new Date(s[3]), Double.parseDouble(s[4]));
        }
        return null;
    }

    private Souvenir parseUkrPost(String[] s) {
        String souvenirType = s[1].toLowerCase();
        if (souvenirType.contains("cup")) {
            return new UkrPost().createCup(s[1], new UkrPost(s[2]), new Date(s[3]), Double.parseDouble(s[4]));
        }
        if (souvenirType.contains("pen")) {
            return new UkrPost().createPen(s[1], new UkrPost(s[2]), new Date(s[3]), Double.parseDouble(s[4]));
        }
        return null;
    }

    public Souvenir parseKyivCity(String[] s) {
        //String souvenirType = s[1].toLowerCase();
        return new KyivCityPostcard(s[1], new KyivCity(s[2]), new Date(s[3]), Double.parseDouble(s[4]));

    }

    private Souvenir parsePrivatBank(String[] s) {
        String souvenirType = s[1].toLowerCase();
        if (souvenirType.contains("keychain")) {
            return new PrivatBank().createKeychain(s[1], new PrivatBank(s[2]), new Date(s[3]), Double.parseDouble(s[4]));
        }
        return null;
    }

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
                .collect(Collectors.groupingBy(souvenir -> souvenir.getDate().getYear() + 1900));
    }

    public List<Souvenir> removeSouvenirByProducer(List<Souvenir> souvenirs, String producer) {
        souvenirs.removeIf(souvenir -> souvenir.getProducer().getName().equals(producer));
        return souvenirs.subList(0, souvenirs.size());
    }

    //method to write in file
}
