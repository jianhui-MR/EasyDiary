package com.bobby.mydiary;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.bobby.DaoMaster;
import com.bobby.DaoSession;
import com.bobby.NoteDao;

public class MainActivity extends ListActivity {
    private SharedPreferences textpassword = null;
    private String password = null;
    private boolean isSet = false;
    private EditText checkpass = null;
    boolean isFirstIn = false;
    public static final int MENU_ITEM_INSERT = Menu.FIRST;
    public static final int MENU_ITEM_EDIT = Menu.FIRST + 1;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private NoteDao noteDao;

    private Cursor cursor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_list);
        InitDAO();
        InitList();
    }
    private void InitDAO() {
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db",
                null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();

    }

    private void InitList() {
        String textColumn = NoteDao.Properties.Title.columnName;
        String dateColunm = NoteDao.Properties.Date.columnName;
        String orderBy = dateColunm + " COLLATE LOCALIZED DESC";
        cursor = db.query(noteDao.getTablename(), noteDao.getAllColumns(),
                null, null, null, null, orderBy);
        String[] from = { textColumn, dateColunm };
        int[] to = { R.id.text1, R.id.created };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.diary_row, cursor, from, to);
        setListAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_ITEM_INSERT, 0, R.string.menu_insert);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_INSERT:
                Intent intent0 = new Intent(this, DiaryEditor.class);
                intent0.setAction(DiaryEditor.INSERT_DIARY_ACTION);
                intent0.setData(getIntent().getData());
                startActivity(intent0);
                MainActivity.this.finish();
                cursor.requery();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        // Uri uri = ContentUris.withAppendedId(getIntent().getData(), id);
        String mid = Long.toString(id);
        Log.d("id", mid);
        // Intent intent = new Intent();
        // startActivity(new Intent(DiaryEditor.EDIT_DIARY_ACTION, uri));
        Intent intent = new Intent(this, DiaryEditor.class);
        intent.setAction(DiaryEditor.EDIT_DIARY_ACTION);
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        intent.putExtras(bundle);
        Log.d("id", mid);
        startActivity(intent);
        MainActivity.this.finish();
        cursor.requery();
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        cursor.requery();
        super.onActivityResult(requestCode, resultCode, intent);
        // renderListView();
    }
    // @SuppressWarnings("deprecation")
    // private void renderListView() {
    // Cursor cursor = managedQuery(getIntent().getData(), PROJECTION,
    // null,null, DiaryColumns.DEFAULT_SORT_ORDER);
    //
    // SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
    // R.layout.diary_row, cursor, new String[] { DiaryColumns.TITLE,
    // DiaryColumns.CREATED }, new int[] { R.id.text1,R.id.created });
    // setListAdapter(adapter);
    // }
}
