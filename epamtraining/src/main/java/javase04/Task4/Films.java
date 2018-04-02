package javase04.Task4;

import java.io.Serializable;
import java.util.*;

public class Films implements Serializable {
    private Map<String, List<String>> filmsToActors;

    public Films(Map<String, List<String>> filmsToActors) {
        this.filmsToActors = filmsToActors;
    }

    public Map<String, List<String>> getFilmsToActors() {
        return filmsToActors;
    }

    public void setFilmsToActor(Map<String, List<String>> filmsToActors) {
        if (filmsToActors != null) {
            this.filmsToActors = filmsToActors;
        }
    }

    public void setActorsToFilm(String film, List<String> actors) {
        if (film != null && actors != null) {
            filmsToActors.get(film).addAll(actors);
        }
    }

    public void addActorToFilm(String filmName, String actorName) {
        if (filmName != null && filmsToActors.containsKey(filmName)) {
            filmsToActors.get(filmName).add(actorName);
        }
    }

    public void removeActorFromFilm(String filmName, String actorName) {
        if (filmName != null && filmsToActors.containsKey(filmName)) {
            filmsToActors.get(filmName).remove(actorName);
        }
    }

    public void addFilmAndActors(String filmName, List<String> actors) {
        if (filmName != null && !filmsToActors.containsKey(filmName)) {
            filmsToActors.put(filmName, actors);
        }
    }

    public void addFilm(String filmName) {
        if (filmName != null && !filmsToActors.containsKey(filmName)) {
            filmsToActors.put(filmName, new ArrayList<String>());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Films)) return false;
        Films films = (Films) o;
        return Objects.equals(getFilmsToActors(), films.getFilmsToActors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFilmsToActors());
    }
}
