package com.player.MusicPlayerApp.serviceImpl;

import com.player.MusicPlayerApp.model.Song;
import com.player.MusicPlayerApp.playlistExport.ExportFormat;
import com.player.MusicPlayerApp.playlistExport.PlaylistExporter;
import com.player.MusicPlayerApp.reader.FileType;
import com.player.MusicPlayerApp.reader.PlaylistFileReader;
import com.player.MusicPlayerApp.reader.PlaylistFileReaderImpl;
import com.player.MusicPlayerApp.repository.SongRepository;
import com.player.MusicPlayerApp.repositoryImpl.SongRepositoryImpl;
import com.player.MusicPlayerApp.service.MusicPlayerSerivce;

import java.io.IOException;
import java.util.List;

public class MusicPlayerServiceImpl implements MusicPlayerSerivce {

    private SongRepository songRepository;
    private SongRepositoryImpl songRepositoryImpl;
    private PlaylistExporter playlistExporter;
    private PlaylistFileReader fileReader;

    public MusicPlayerServiceImpl(SongRepository songRepository, PlaylistExporter playlistExporter, PlaylistFileReader fileReader) {
        this.songRepository = songRepository;
        this.playlistExporter = playlistExporter;
        this.fileReader = fileReader;
    }


    @Override
    public void addSong(String name, String artist) {
        if(name.trim().isEmpty() || artist.trim().isEmpty()) {
            System.out.println("Invalid input");
            return;
        }
        //String key = (name.trim() + "::" + artist.trim()).toLowerCase();

        else {
            songRepository.addSong(name, artist);
        }
    }

    @Override
    public void playSong(String name, String artist) {

        if(name.trim().isEmpty() || artist.trim().isEmpty()) {
            System.out.println("Invalid input");
            return;
        }

//        Song song = new Song(name, artist);
//        song.incrementPlayCount();



        songRepository.playSong(name, artist);
    }

    @Override
    public Song findSong(String name, String artist) {
        Song song = songRepository.findSong(name, artist);
        //if(song == null) {System.out.println("Song not found");}
        return song;
    }

    @Override
    public List<Song> getTopSongs(int num) {
        return songRepository.getTopSongs(num);
    }

    @Override
    public List<Song> getTopArtistSongs(String artist, int num) {
        return songRepository.getTopSongsByArtist(artist, num);
    }

    @Override
    public List<Song> getTopDailySongs(int num) {
        return songRepository.topDailySongs(num);
    }

    @Override
    public List<Song> getSongsByArtist(String artist) {
        return songRepository.getSongsByArtist(artist);
    }

    @Override
    public void exportPlaylist(List<Song> songs, String filePath, ExportFormat format) throws IOException {

        if (songs == null || songs.isEmpty()) {
            throw new IllegalArgumentException("Song list cannot be empty");
        }
        playlistExporter.exportPlaylist(songs, filePath, format);
    }

    @Override
    public List<Song> readFromFile(String filePath) throws IOException {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be empty");
        }

        FileType fileType = FileType.fromFileName(filePath);
        return fileReader.readFile(filePath, fileType);
    }

    private String truncateString(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }

    @Override
    public void displaySongs(List<Song> songs) {
        if (songs == null || songs.isEmpty()) {
            System.out.println("No songs to display");
            return;
        }

        System.out.println("\nSong List:");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-30s %-20s %-10s%n", "Name", "Artist", "Play Count");
        System.out.println("------------------------------------------------------------");

        for (Song song : songs) {
            System.out.printf("%-30s %-20s %-10d%n",
                    truncateString(song.getName(), 29),
                    truncateString(song.getArtist(), 19),
                    song.getPlayCount());
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Total songs: " + songs.size());
    }


    @Override
    public List<Song> getAll() {
        return songRepository.getAllSongs();
    }
}
