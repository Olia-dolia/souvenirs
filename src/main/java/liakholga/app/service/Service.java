package liakholga.app.service;

import com.opencsv.*;

import liakholga.app.producers.*;
import liakholga.app.souvenirInterface.Souvenir;
import liakholga.app.souvenirs.KyivCityPostcard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    private static Service instance = new Service();

    private Service() {
    }

    public static Service getInstance() {
        if (instance == null) instance = new Service();
        return instance;
    }

    /**
     * Get all files from directory
     *
     * @throws IOException if bad things happen
     */
    private List<Path> getFilesName() throws IOException {
        return Files.list(Path.of("src/main/resources/inputData")).toList();
    }

    /**
     * Get data from files
     *
     * Read with opencsv
     *
     * @throws IOException if bad things happen
     */
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

    /**
     * Get Souvenirs list from Souvenir
     * @param fromFile
     * a list of strings arrays with each element as a separate entry.
     */
    public List<Souvenir> parseSouvenirInputList(List<String[]> fromFile) {
        List<Souvenir> souvenirs = new ArrayList<>();
        for (String[] str : fromFile) {
            souvenirs.add(parseProducer(str));
        }
        return souvenirs;
    }

    /**
     * Get and Parse Producer
     * @param s
     * a list of strings array with each element as a separate entry.
     */
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

    /**
     * Get and Parse NovaPost souvenirs
     * @param s
     * a list of strings array with each element as a separate entry.
     */
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

    /**
     * Get and Parse UkrPost souvenirs
     * @param s
     * a list of strings array with each element as a separate entry.
     */
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

    /**
     * Get and Parse KyivCity souvenirs
     * @param s
     * a list of strings array with each element as a separate entry.
     */
    public Souvenir parseKyivCity(String[] s) {
        //String souvenirType = s[1].toLowerCase();
        return new KyivCityPostcard(s[1], new KyivCity(s[2]), new Date(s[3]), Double.parseDouble(s[4]));

    }

    /**
     * Get and Parse PrivatBank souvenirs
     * @param s
     *      a list of strings array with each element as a separate entry.
     */
    private Souvenir parsePrivatBank(String[] s) {
        String souvenirType = s[1].toLowerCase();
        if (souvenirType.contains("keychain")) {
            return new PrivatBank().createKeychain(s[1], new PrivatBank(s[2]), new Date(s[3]), Double.parseDouble(s[4]));
        }
        return null;
    }

    /**
     *
     * @param souvenirs
     *          list of souvenirs obj
     * @return list of string about producers
     */
    public List<String> getAllProducers(List<Souvenir> souvenirs) {
        return souvenirs.stream().map(x -> x.getProducer()
                .getName())
                .distinct()
                .toList();
    }

    /**
     * Print list of souvenirs
     * @param souvenirs
     *      print list of souvenirs obj
     */
    public void getAllSouvenirs(List<Souvenir> souvenirs) {
        souvenirs.forEach(System.out::println);
    }

    public void terminalOutput(List<String> list) {
        list.forEach(System.out::println);
    }

    /**
     * Print all souvenirs by certain country
     * @param souvenirs
     *          list of souvenirs obj
     * @param country
     *          string of certain country
     */
    public void getSouvenirsByCountry(List<Souvenir> souvenirs, String country) {
        souvenirs.stream().filter(souvenir -> souvenir.getProducer().getCountry().equals(country))
                .forEach(System.out::println);
    }

    /**
     * Print all producers info where price on souvenirs < certain_price
     * @param souvenirs
     *          list of souvenirs obj
     * @param price
     *          double price of certain country
     */
    public void getProducerByPrice(List<Souvenir> souvenirs, double price) {
        souvenirs.stream().filter(x -> x.getPrice() < price)
                .forEach(producer -> System.out.printf("Producer - %s, country - \"%s\" \n",
                        producer.getProducer().getName(), producer.getProducer().getCountry()));
    }

    /**
     * Update certain souvenir
     * @param souvenirs
     *          list of souvenirs obj
     * @param name
     *          string name of certain souvenir
     * @param producer
     *          obj name and string country of certain souvenir
     * @return updated obj
     */
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
        newPrice = Double.parseDouble(scanner.nextLine());

        return souvenir.setSouvenir(newName, producer, new Date(newDate), newPrice);
    }

    /**
     * Update certain souvenir
     * @param souvenirs
     *          list of souvenirs obj
     * @param name
     *          string name of certain souvenir
     * @param producer
     *          obj name and string country of certain souvenir
     * @return index of souvenir to change
     */
    public int getIndex(List<Souvenir> souvenirs, String name, Producer producer) {
        //find S in list
        int index = 0;
        for (Souvenir s : souvenirs) {
            if (s.getName().equals(name) &&
                    (s.getProducer().getName().equals(producer.getName())
                    && s.getProducer().getCountry().equals(producer.getCountry()))) {
                //get index
                return souvenirs.indexOf(s);
            }
        }
        return index;
    }

    /**
     *
     * @param souvenirs
     *          list of souvenirs obj
     * @return Map key - Producer Name, value - list of Souvenirs
     */
    public Map<String, List<Souvenir>> getProducerAndItsSouvenirs(List<Souvenir> souvenirs) {
        return souvenirs.stream()
                .collect(Collectors.groupingBy(souvenir -> souvenir.getProducer().getName()));
    }

    /**
     *
     * @param map
     *          Map key - Any Type, value - list of Souvenirs
     * @param choice
     *          variant of print
     * @param <T>
     *           could be case 1 -> String, key - print without Producer
     *           could be case 2 -> Integer, key - full print
     */
    public <T> void printMap(Map<T, List<Souvenir>> map, int choice) {
        for (Map.Entry<T, List<Souvenir>> entry : map.entrySet()) {
            System.out.println("\t" + entry.getKey());
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

    /**
     *
     * @param souvenirs
     *          list of souvenirs obj
     * @param name
     *          type of souvenir
     * @param year
     *          int certain year
     */
    public void getProducerByYearAndSouvenir(List<Souvenir> souvenirs, String name, int year) {
        System.out.println("For product - " + name +" in " + year + " year");
        souvenirs.stream().filter(x -> (x.getDate().getYear() + 1900) == year && x.getName().toLowerCase().contains(name))
                .forEach(x -> System.out.printf("Producer - %s, country - \"%s\" \n",
                        x.getProducer().getName(), x.getProducer().getCountry()));
    }

    /**
     *
     * @param souvenirs
     *          list of souvenirs obj
     * @return
     *          Map key - int year, value - list of souvenirs
     */
    public Map<Integer, List<Souvenir>> getSouvenirsByYear(List<Souvenir> souvenirs) {
        return souvenirs.stream()
                .collect(Collectors.groupingBy(souvenir -> souvenir.getDate().getYear() + 1900));
    }

    /**
     *
     * @param souvenirs
     *          list of souvenirs obj
     * @param producer
     *          producer name to remove
     * @return
     *          list of souvenirs
     */
    public List<Souvenir> removeSouvenirsByProducer(List<Souvenir> souvenirs, String producer) {
        souvenirs.removeIf(souvenir -> souvenir.getProducer().getName().equals(producer));
        return souvenirs.subList(0, souvenirs.size());
    }

    /**
     * Create folder or check if exists
     * @return
     *          true - create or exists
     *          false - IOException
     */
    private boolean createFolder() {
        String path = "src/main/resources/outputData";
        File file = new File(path);
        try {
            if (!file.exists()) {
                return file.mkdir();
            } else {
                return file.mkdir();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Create file
     * @return
     *          file
     */
    private File createFile() {
        String path = "src/main/resources/outputData";
        String fileName = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).concat("-Output.csv");
        File file = new File(path.concat("/" + fileName));

        try {
            boolean create = file.createNewFile();
            if (create) {
                return file;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Write in csv file use opencsv
     * @param file
     *          file created
     * @param souvenirs
     *          a list of strings array with each element as a separate entry.
     * @return
     *          true - if write success
     *          false - IOException
     */
    private boolean writeInCsvFile(File file, List<String[]> souvenirs) {
        String[] header = new String[]{"producer", "souvenir_name", "country", "date", "price"};
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            writer.writeNext(header, false);
            writer.writeAll(souvenirs, false);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Parse Souvenir obj in Strings Array and write in CSV
     * @param souvenirs
     *          list of souvenirs obj
     * @return
     *          true - if folder, file were created and all were written
     *          false - IOException
     */
    public boolean writeInFile(List<Souvenir> souvenirs) {
        List<String[]> list = new ArrayList<>();

        for (Souvenir souvenir : souvenirs) {
            String[] s = new String[]{souvenir.getProducer().getName(),
                    souvenir.getName(), souvenir.getProducer().getCountry(), souvenir.getDate().toString(),
                    String.valueOf(souvenir.getPrice())};
            list.add(s);
        }

        if (createFolder()) {
            return writeInCsvFile(createFile(), list);
        }

        return false;
    }
}
