package com.brightsoft.dao.tms;

import com.brightsoft.model.VehicleTrackRecord;

public interface VehicleTrackRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VehicleTrackRecord record);

    int insertSelective(VehicleTrackRecord record);

    VehicleTrackRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VehicleTrackRecord record);

    int updateByPrimaryKey(VehicleTrackRecord record);
}