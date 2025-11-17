//package com.rokaly.sge.dto;
//
//import com.rokaly.sge.model.Forklift;
//import com.rokaly.sge.model.History;
//import com.rokaly.sge.model.Type;
//
//public record GetHistoryDTO(
//        Long id,
//        Double hourMeter,
//        String description,
//        String dateTime,
//        Long idForklift,
//        String series,
//        String internalCode,
//        Type type
//) {
//
//    public GetHistoryDTO(History h) {
//        this(h.getId(), h.getHourMeter(), h.getDescription(), h.getDateTime(), h.getForklift().getId(), h.getForklift().getSeries(), h.getForklift().getInternalCode(), h.getForklift().getType());
//    }
//}
