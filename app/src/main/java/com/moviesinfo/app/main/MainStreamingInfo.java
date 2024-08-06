package com.moviesinfo.app.main;

import com.moviesinfo.app.models.Episode;
import com.moviesinfo.app.models.EpisodesData;
import com.moviesinfo.app.models.SeasonData;
import com.moviesinfo.app.models.SeriesData;
import com.moviesinfo.app.service.APIConsumer;
import com.moviesinfo.app.service.DataConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainStreamingInfo {

    private Scanner reader = new Scanner(System.in);
    private APIConsumer apiConsumer = new APIConsumer();
    private DataConverter dataConverter = new DataConverter();

    private static final String URL = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=d996cde6";

    public void showMenu() {

        System.out.println("\n");
        System.out.println("Type your favorite tv show: ");
        var serieName = reader.nextLine();
        var json  = apiConsumer.getData(
                URL + serieName.replace(" ", "+") + API_KEY
        );
        SeriesData seriesdata = dataConverter.getData(json, SeriesData.class);
        System.out.println("\n" + seriesdata + "\n");


        List<SeasonData> seasons = new ArrayList<>();

        // Nesse laço de repetição percorremos o atributo seasons de seriesdata, executando as ações para cada season:
        // tratamos a pesquisa, substituindo os espaços pelo sinal "+"
        // consvertendo os resultado de cada laço de repetiçãoo e adicionando a lista SeasonData

        for (int i =1; i<= seriesdata.seasons(); i++) {
            json = apiConsumer.getData(
                    URL + serieName.replace(" ", "+") + "&season=" + i + API_KEY
            );
            SeasonData seasonData = dataConverter.getData(json, SeasonData.class);
            seasons.add(seasonData);
        }

        // Essa também é uma função lambda, no qual uma ação é executada sobre cada elemento da lista.
        // Nesse caso a ação executada sobre cada elemento é imprimir na tela
        seasons.forEach(System.out::println);
        System.out.println("\n");

        // Função lambda -> pesquisa sobre a lógica e sua sintaxe
        // Nessa função lambda percorremos as temporadas e então percorremos os episodios, imprimindo na tela
        seasons.forEach(s -> s.episodesDataList().forEach(e -> System.out.println(e.title())));

        List<EpisodesData> episodesDataList = seasons.stream()
                .flatMap(s -> s.episodesDataList().stream())
                .collect(Collectors.toList());

        System.out.println("\n");
        episodesDataList.stream()
                .filter(e -> !e.rating().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(EpisodesData::rating).reversed())
                .limit(5)
                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(s -> s.episodesDataList().stream()
                        .map(d -> new Episode(s.season(), d))
                ).collect(Collectors.toList());

        System.out.println("\n");
        episodes.forEach(System.out::println);

        System.out.println("A partir de que ano você deseja ver os episódios? ");
        var year  = reader.nextInt();
        reader.nextLine();


        LocalDate dateSearching = LocalDate.of(year, 1,1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodes.stream()
                .filter(e -> e.getRelease() != null && e.getRelease().isAfter(dateSearching))
                .forEach(e -> System.out.println(
                        "Season: " + e.getSeason() +
                                " Episode: " + e.getTitle() +
                                " Released: " + e.getRelease().format(formatter)
                ));


    }
}
