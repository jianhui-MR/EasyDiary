package com.bobby.mydiary;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Fields {
    public static final String AUTHORITY = "com.bobby.mydiary.DiaryContentProvider";
    private Fields() {}
    public static final class DiaryColumns implements BaseColumns {

        private DiaryColumns() {}

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/diaries");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.diary";

        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.diary";

        public static final String DEFAULT_SORT_ORDER = "created DESC";

        public static final String TITLE = "title";

        public static final String BODY = "body";

        public static final String CREATED = "created";   
    }
}

