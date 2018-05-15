package cz.cvut.fit.SemStew.backend;

import JOOQ.tables.records.ReservationRecord;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.ReservationService;

import java.util.ArrayList;
import java.util.List;

public class ReservationController {

    private final ReservationService reservationService = new ReservationService();

    public ReservationController() {
    }

    private List<ReservationRepresentation> LoadData(){
        List<ReservationRepresentation> reservationRepresentations = new ArrayList<>();

        List<ReservationRecord> reservationRecords = reservationService.getConfigs();

        for(ReservationRecord rec : reservationRecords){
            reservationRepresentations.add(new ReservationRepresentation());
            reservationRepresentations.get(reservationRepresentations.size() - 1).LoadData(rec);
        }

        return reservationRepresentations;
    }

    public void Insert(ReservationRepresentation insert){
        reservationService.InsertReservationService(insert.GetReservationRecord());
    }

    public void Update(ReservationRepresentation update){
        reservationService.UpdateReservationService(update.GetReservationRecord());
    }

    public void Delete(ReservationRepresentation delete){
        reservationService.DeleteReservationService(delete.GetReservationRecord());
    }

    public ReservationRepresentation GetById(int id){
        ReservationRepresentation tmp = new ReservationRepresentation();
        tmp.LoadData(reservationService.GetById(id));
        return tmp;
    }

    public List<ReservationRepresentation> getConfigs(){
        return LoadData();
    }
}