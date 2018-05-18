package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.ReservationRecord;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.ReservationService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class ReservationController {

    /**
     * Reservation management
     */
    private final ReservationService reservationService = new ReservationService();

    /**
     *  ReservationController constructor
     */
    public ReservationController() {
    }

    /**
     * Get all ReservationRepresentations
     *
     * Use {@link #LoadData()} to get all ReservationRepresentations from database
     *
     * @return list of all ReservationRepresentations
     */
    private List<ReservationRepresentation> LoadData(){
        List<ReservationRepresentation> reservationRepresentations = new ArrayList<>();

        List<ReservationRecord> reservationRecords = reservationService.getConfigs();

        for(ReservationRecord rec : reservationRecords){
            reservationRepresentations.add(new ReservationRepresentation());
            reservationRepresentations.get(reservationRepresentations.size() - 1).LoadData(rec);
        }

        return reservationRepresentations;
    }

    /**
     * Insert ReservationRepresentation
     *
     * Use {@link #Insert(ReservationRepresentation)} to insert given ReservationRepresentation
     *
     * @param insert ReservationRepresentation to be inserted
     */
    public void Insert(ReservationRepresentation insert){
        reservationService.InsertReservationService(insert.GetReservationRecord());
    }

    /**
     * Update ReservationRepresentation
     *
     * Use {@link #Update(ReservationRepresentation)} to update given ReservationRepresentation
     *
     * @param update ReservationRepresentation to be updated
     */
    public void Update(ReservationRepresentation update){
        reservationService.UpdateReservationService(update.GetReservationRecord());
    }

    /**
     * Delete ReservationRepresentation
     *
     * Use {@link #Delete(ReservationRepresentation)} to delete given ReservationRepresentation
     *
     * @param delete ReservationRepresentation to be deleted
     */
    public void Delete(ReservationRepresentation delete){
        reservationService.DeleteReservationService(delete.GetReservationRecord());
    }

    /**
     * Get ReservationRepresentation by ID
     *
     * Use {@link #GetById(int)} to get ReservationRepresentation by given ID
     *
     * @param id ID of searched ReservationRepresentation
     * @return ReservationRepresentation of given ID if it exists, else null
     */
    public ReservationRepresentation GetById(int id){
        ReservationRepresentation tmp = new ReservationRepresentation();
        ReservationRecord record = reservationService.GetById(id);
        if(record == null)
            return null;
        tmp.LoadData(record);
        return tmp;
    }

    /**
     * Get all ReservationRepresentations
     *
     * Use {@link #getConfigs()} to get all ReservationRepresentations from database
     *
     * @return list of all ReservationRepresentations
     */
    public List<ReservationRepresentation> getConfigs(){
        return LoadData();
    }
}