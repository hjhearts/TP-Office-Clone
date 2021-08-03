package org.office.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Company {
    ASIANA_AIRLINE("AA", "아시아나 항공", "ASIANA_AIRLINE"),
    ASIANA_IDT("AI", "아시아나 IDT", "ASIANA_IDT"),
    ASIANA_SABRE("AS", "아시아나 세이버", "ASIANA_SABRE"),
    AIR_SEOUL("SA", "에어서울", "AIR_SEOUL"),
    AIR_PUSAN("PA", "에어부산", "AIR_PUSAN");


    private final String key;
    private final String korCompanyName;
    private final String engCompanyName;
}
