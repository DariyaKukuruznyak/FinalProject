package com.kukuruznyak.bettingcompany.util;

public interface StringMessages {
    /**
     * Web messages
     */
    String ACCESS_DENIED = "access_denied";
    String EMPTY_BASKET = "empty_basket";
    String BET_PLACED = "bet_placed";
    String UNEXPECTED_REQUEST = "unexpected_request";
    String FORBIDDEN_COEFFICIENT = "forbidden_coefficient";
    String COEFFICIENT_CHANGED_SUCCESSFULLY = "coefficient_changed_successfully";
    String TOURNAMENT_RESULT_UNKNOWN = "tournament_result_unknown";
    String EVENT_STATUS_CHANGED = "event_status_changed";
    String NO_TOURNAMENT_SELECTED = "no_tournament_selected";
    String EVENT_CREATED_SUCCESSFULLY = "event_created_successfully";
    String PARTICIPANT_CREATED_SUCCESSFULLY = "participant_created_successfully";
    String PARTICIPANT_UPDATED_SUCCESSFULLY = "participant_updated_successfully";
    String INCORRECT_PARTICIPANT = "incorrect_participant";
    String TOURNAMENT_CREATED_SUCCESSFULLY = "tournament_created_successfully";
    String TOURNAMENT_UPDATED_SUCCESSFULLY = "tournament_updated_successfully";
    String INCORRECT_TOURNAMENT = "incorrect_tournament";
    String USER_LOGGED_OUT = "user_logged_out";
    String USER_SIGNED_IN = "user_signed_in";
    String USER_EXIST = "user_exist";
    String USER_CREATED_SUCCESSFULLY = "user_created_successfully";
    String USER_UPDATED_SUCCESSFULLY = "user_updated_successfully";
    String PASSWORDS_NOT_EXIST = "passwords_not_equals";
    String INCORRECT_USER = "incorrect_user";
    String CLIENT_JOINED = "client_joined";
    String INCORRECT_INPUT = "incorrect_input";
    String NO_PAGE_IDENTIFIED = "no_page_identified";
    String INCORRECT_USER_REQUEST = "incorrect_request";
    /**
     * DataBase messages
     */
    String CONNECTION_POOL_INITIALIZED = "connection_pool_initialized";
    String DB_INITIALIZATION_ERROR = "database_initialization_error";
    String DB_INSERTING_ERROR = "database_inserting_error";
    String DB_UPDATING_ERROR = "database_updating_error";
    String DB_SELECTING_ERROR = "database_selecting_error";
    String DB_REMOVING_ERROR = "database_removing_error";
    String LINKED_TABLE_DB_ERROR = "linked_table_database_error";
    String NOT_FOUND = "not_found";
    String MESSAGE = "error_message";
}
