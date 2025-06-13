package mole.trasformazioni;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

/**
 * Una classe che implementa {@link Function} per trasformare un {@link LocalDateTime}
 * in un {@link LocalTime}.
 */
public class MinutiOre implements Function<LocalDateTime, LocalTime> {

    /**
     * Crea una funzione che trasforma un {@link LocalDateTime} in un {@link LocalTime}.
     */
    public MinutiOre() {}

    
    /**
     * Trasforma un LocalDateTime in un LocalTime.
     * 
     * @param data l'oggetto LocalDateTime da trasformare
     * @return un oggetto LocalTime che rappresenta il tempo di {@code data}
     */
    @Override
    public LocalTime apply(LocalDateTime data) {
        return data.toLocalTime();
    }

}
