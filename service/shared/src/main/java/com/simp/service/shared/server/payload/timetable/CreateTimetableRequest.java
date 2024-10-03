package com.simp.service.shared.server.payload.timetable;

import java.util.Date;

public record CreateTimetableRequest(int hospitalId, int doctorId, Date from, Date to, String room) {
}
