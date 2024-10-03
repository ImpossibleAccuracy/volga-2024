package com.simp.service.shared.data.contants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class Services {
    public static final class Account {
        public static final String Name = "auth-service";
        public static final String Key = "app.services.auth";
    }

    public static final class Hospital {
        public static final String Name = "hospital-service";
        public static final String Key = "app.services.hospital";
    }
}
