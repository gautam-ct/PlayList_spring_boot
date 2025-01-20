package com.player.MusicPlayerApp;

import com.player.MusicPlayerApp.model.Song;
import com.player.MusicPlayerApp.playlistExport.ExportFormat;
import com.player.MusicPlayerApp.playlistExport.PlaylistExporter;
import com.player.MusicPlayerApp.playlistExport.PlaylistExporterImpl;
import com.player.MusicPlayerApp.reader.PlaylistFileReader;
import com.player.MusicPlayerApp.reader.PlaylistFileReaderImpl;
import com.player.MusicPlayerApp.repository.SongRepository;
import com.player.MusicPlayerApp.repositoryImpl.SongRepositoryImpl;
import com.player.MusicPlayerApp.service.MusicPlayerSerivce;
import com.player.MusicPlayerApp.serviceImpl.MusicPlayerServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//@SpringBootApplication
public class MusicPlayerAppApplication {

	public static void main(String[] args) {

		//SpringApplication.run(MusicPlayerAppApplication.class, args);
		SongRepository songRepository = new SongRepositoryImpl();
		PlaylistExporter playlistExporter = new PlaylistExporterImpl();
		PlaylistFileReader playlistFileReader = new PlaylistFileReaderImpl();
		MusicPlayerSerivce musicPlayerSerivce = new MusicPlayerServiceImpl(songRepository, playlistExporter, playlistFileReader);

		musicPlayerSerivce.addSong("Beete Lamhe", "KK");
		musicPlayerSerivce.addSong("Tu hi meri shab hai", "KK");
		musicPlayerSerivce.playSong("Beete Lamhe", "KK");
		musicPlayerSerivce.addSong("Khuda Jane", "KK");
		musicPlayerSerivce.addSong("Be intehaan", "Atif Aslam");
		musicPlayerSerivce.addSong("Uska hi banana", "Arijit Singh");
		musicPlayerSerivce.addSong("Labon ko", "KK");
		musicPlayerSerivce.addSong("Haan tu hai", "KK");
		musicPlayerSerivce.addSong("kabhi na kabhi", "aditya narayan");
		musicPlayerSerivce.addSong("kuch iss tarah", "atif aslam");
		musicPlayerSerivce.addSong("tere ishq mein", "aditya yadav");
		musicPlayerSerivce.addSong("zara sa", "KK");


		musicPlayerSerivce.playSong("khuda jane", "KK");
		musicPlayerSerivce.playSong("Khuda Jane", "kk");
		musicPlayerSerivce.playSong("zara sa", "kk");
		musicPlayerSerivce.playSong("kuch iss tarah", "atif aslam");
		musicPlayerSerivce.playSong("tere ishq mein", "aditya yadav");
		musicPlayerSerivce.playSong("zara sa", "kk");
		musicPlayerSerivce.playSong("zara sa", "kk");
		musicPlayerSerivce.playSong("kuch iss tarah", "atif aslam");
		musicPlayerSerivce.playSong("kuch iss tarah", "atif aslam");
		musicPlayerSerivce.playSong("kuch iss tarah", "atif aslam");
		musicPlayerSerivce.addSong("maula mere maula", "roop kumar rathod");
		musicPlayerSerivce.playSong("maula mere maula", "roop kumar rathod");
		musicPlayerSerivce.playSong("maula mere maula", "roop kumar rathod");
		musicPlayerSerivce.playSong("maula mere maula", "roop kumar rathod");
		musicPlayerSerivce.playSong("maula mere maula", "roop kumar rathod");

		musicPlayerSerivce.addSong("", "");
		musicPlayerSerivce.addSong("", "deepak");
		musicPlayerSerivce.addSong("lol", "");
		System.out.println("Artist total song : " + musicPlayerSerivce.getSongsByArtist("kk"));
		System.out.println("Artist total song : " + musicPlayerSerivce.getSongsByArtist("atif aslam"));
		System.out.println("total song : " + musicPlayerSerivce.getAll());

		musicPlayerSerivce.addSong("Be intehaan", "Atif Aslam");
		musicPlayerSerivce.playSong("Khuda Jane", "lol");

		Song song = musicPlayerSerivce.findSong("Beete Lamhe", "KK");
		System.out.println(song.getName());

		Song song2 = musicPlayerSerivce.findSong("Beete Lamhe", "JK");
		//System.out.println(song);


		System.out.println("Daily Top Songs : " + musicPlayerSerivce.getTopDailySongs(2));
		System.out.println("Top Songs : " +musicPlayerSerivce.getTopSongs(2));
		System.out.println("Top Songs of Artist : " +musicPlayerSerivce.getTopArtistSongs("kk", 2));
		List<Song> list1 = musicPlayerSerivce.getAll();

		try{


			musicPlayerSerivce.exportPlaylist(list1, "/Users/deepak.verma/Desktop/playlists/list1.csv", ExportFormat.CSV);
			System.out.println("CSV file exported successfully");
		} catch (IOException e) {
			System.out.println("Error while exporting csv file");
		}

		try{


			musicPlayerSerivce.exportPlaylist(list1, "/Users/gautam.pareek/Desktop/playlists/list1.csv", ExportFormat.CSV);
			System.out.println("CSV file exported successfully");
		} catch (IOException e) {
			System.out.println("Error while exporting csv file");
		}

		try{
			System.out.println("\nReading CSV file:");
			List<Song> csvSongs = musicPlayerSerivce.readFromFile("/Users/gautam.pareek/Desktop/playlists/list1.csv");
			musicPlayerSerivce.displaySongs(csvSongs);

		} catch (Exception e) {
			System.err.println("Error reading file: " + e.getMessage());
			e.printStackTrace();
		}



	}

}
