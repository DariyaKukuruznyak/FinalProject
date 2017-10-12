package com.kukuruznyak.bettingcompany.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class StringMessages {
    /**
     * Web messages
     */
    public static final String ACCESS_DENIED = "access_denied";
    public static final String EMPTY_BASKET = "empty_basket";
    public static final String BET_PLACED = "bet_placed";
    public static final String BET_NOT_PLACED= "bet_not_placed";
    public static final String UNEXPECTED_REQUEST = "unexpected_request";
    public static final String FORBIDDEN_COEFFICIENT = "forbidden_coefficient";
    public static final String COEFFICIENT_CHANGED_SUCCESSFULLY = "coefficient_changed_successfully";
    public static final String TOURNAMENT_RESULT_UNKNOWN = "tournament_result_unknown";
    public static final String EVENT_STATUS_CHANGED = "event_status_changed";
    public static final String NO_TOURNAMENT_SELECTED = "no_tournament_selected";
    public static final String EVENT_CREATED_SUCCESSFULLY = "event_created_successfully";
    public static final String PARTICIPANT_CREATED_SUCCESSFULLY = "participant_created_successfully";
    public static final String PARTICIPANT_UPDATED_SUCCESSFULLY = "participant_updated_successfully";
    public static final String INCORRECT_PARTICIPANT = "incorrect_participant";
    public static final String TOURNAMENT_CREATED_SUCCESSFULLY = "tournament_created_successfully";
    public static final String TOURNAMENT_UPDATED_SUCCESSFULLY = "tournament_updated_successfully";
    public static final String INCORRECT_TOURNAMENT = "incorrect_tournament";
    public static final String USER_LOGGED_OUT = "user_logged_out";
    public static final String USER_SIGNED_IN = "user_signed_in";
    public static final String USER_EXIST = "user_exist";
    public static final String USER_CREATED_SUCCESSFULLY = "user_created_successfully";
    public static final String USER_UPDATED_SUCCESSFULLY = "user_updated_successfully";
    public static final String PASSWORDS_NOT_EXIST = "passwords_not_equals";
    public static final String INCORRECT_USER = "incorrect_user";
    public static final String CLIENT_JOINED = "client_joined";
    public static final String INCORRECT_INPUT = "incorrect_input";
    public static final String NO_PAGE_IDENTIFIED = "no_page_identified";
    public static final String NOT_ENOUGH_MONEY = "not_enough_money";
    public static final String INITIALIZED = "initialized";
    /**
     * DataBase messages
     */
    public static final String CONNECTION_POOL_INITIALIZED = "connection_pool_initialized";
    public static final String DB_INITIALIZATION_ERROR = "database_initialization_error";
    public static final String DB_INSERTING_ERROR = "database_inserting_error";
    public static final String DB_UPDATING_ERROR = "database_updating_error";
    public static final String DB_SELECTING_ERROR = "database_selecting_error";
    public static final String DB_REMOVING_ERROR = "database_removing_error";
    public static final String LINKED_TABLE_DB_ERROR = "linked_table_database_error";
    public static final String NOT_FOUND = "not_found";
    public static final String MESSAGE = "error_message";

    static final String BUNDLE_NAME = "/i18n/string_messages";
    public static final Locale ENGLISH_LOCALE = new Locale("en", "US");
    public static final Locale RUSSIAN_LOCALE = new Locale("ru", "RU");
    static ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, ENGLISH_LOCALE);

    public static void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static String getMessage(String message) {
        return resourceBundle.getString(message);
    }
}
