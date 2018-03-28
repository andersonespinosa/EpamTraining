package JavaSE04.Task4;

import java.io.Serializable;
import java.util.*;

public class Films implements Serializable {
    private Map<String, List<String>> filmsToActors = new HashMap<>();

    public Films(Map<String, List<String>> filmsToActors) {
        this.filmsToActors = filmsToActors;
    }

    public Map<String, List<String>> getFilmsToActors() {
        return filmsToActors;
    }

    public boolean setFilmsToActor(Map<String, List<String>> filmsToActors) {
        boolean result = false;

        if (filmsToActors != null) {
            this.filmsToActors = filmsToActors;
            result = true;
        }
        return result;
    }

    public boolean setActorsToFilm(String film, List<String> actors) {
        boolean result = false;

        if (film != null && actors != null) {
            filmsToActors.get(film).addAll(actors);
            result = true;
        }
        return result;
    }

    public boolean addActorToFilm(String filmName, String actorName) {
        boolean result = false;

        if (filmName != null && filmsToActors.containsKey(filmName)) {
            filmsToActors.get(filmName).add(actorName);
            result = true;
        }
        return result;
    }

    public boolean removeActorFromFilm(String filmName, String actorName) {
        boolean result = false;
        if (filmName != null && filmsToActors.containsKey(filmName)) {
            filmsToActors.get(filmName).remove(actorName);
            result = true;
        }
        return result;
    }

    public boolean addFilmAndActors(String filmName, List<String> actors){
        boolean result = false;
        if(filmName != null && !filmsToActors.containsKey(filmName)){
            filmsToActors.put(filmName, actors);
            result = true;
        }
        return result;
    }

    public boolean addFilm(String filmName){
        boolean result = false;
        if (filmName != null && !filmsToActors.containsKey(filmName)){
            filmsToActors.put(filmName, new ArrayList<String>());
            result = true;
        }
        return result;
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
