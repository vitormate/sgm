//package com.rokaly.sge.dto;
//
//import com.rokaly.sge.model.Forklift;
//import com.rokaly.sge.model.Status;
//import com.rokaly.sge.model.Type;
//
//public record GetForkliftDTO(Long id, String series, String internalCode, Double hourMeter, Type type, Status status) {
//    public GetForkliftDTO(Forklift data) {
//        this(data.getId(), data.getSeries(), data.getInternalCode(), data.getHourMeter(), data.getType(), data.getStatus());
//    }
//}
