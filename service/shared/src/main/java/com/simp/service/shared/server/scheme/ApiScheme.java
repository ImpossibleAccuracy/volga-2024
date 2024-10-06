package com.simp.service.shared.server.scheme;

public final class ApiScheme {
    public static final class AccountService {
        public static final class Auth {
            public static final String SignUp = "/api/Authentication/SignUp";
            public static final String SignIn = "/api/Authentication/SignIn";
            public static final String SignOut = "/api/Authentication/SignOut";
            public static final String Validate = "/api/Authentication/Validate";
            public static final String Refresh = "/api/Authentication/Refresh";
            public static final String Full = "/api/Authentication/Full";
        }

        public static final class Account {
            public static final String Me = "/api/Accounts/Me";
            public static final String Update = "/api/Accounts/Update";
            public static final String Accounts = "/api/Accounts";
            public static final String AccountDetails = "/api/Accounts/{id}";
        }

        public static final class Doctors {
            public static final String Doctors = "/api/Doctors";
            public static final String DoctorDetails = "/api/Doctors/{id}";
        }
    }

    public static final class HospitalsService {
        public static final String Hospitals = "/api/Hospitals";
        public static final String HospitalDetails = "/api/Hospitals/{id}";
        public static final String HospitalRooms = "/api/Hospitals/{id}/Rooms";
    }

    public static final class TimetableService {
        public static final String Timetable = "/api/Timetable";
        public static final String TimetableDetails = "/api/Timetable/{id}";
        public static final String TimetableDoctor = "/api/Timetable/Doctor/{id}";
        public static final String TimetableHospital = "/api/Timetable/Hospital/{id}";
        public static final String TimetableHospitalRoom = "/api/Timetable/Hospital/{id}/Room/{room}";
        public static final String TimetableAppointments = "/api/Timetable/{id}/Appointments";
        public static final String Appointment = "/api/Appointment/{id}";
    }

    public static final class HistoryService {
        public static final String History = "/api/History";
        public static final String HistoryDetails = "/api/History/{id}";
        public static final String HistoryAccount = "/api/History/Account/{id}";
    }
}
