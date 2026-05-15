package net.madscientists.geep.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import net.madscientists.geep.core.database.entity.AttachmentEntity;
import net.madscientists.geep.core.database.util.Converters;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AttachmentDao_Impl implements AttachmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AttachmentEntity> __insertionAdapterOfAttachmentEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<AttachmentEntity> __deletionAdapterOfAttachmentEntity;

  private final EntityDeletionOrUpdateAdapter<AttachmentEntity> __updateAdapterOfAttachmentEntity;

  public AttachmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttachmentEntity = new EntityInsertionAdapter<AttachmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `attachments` (`id`,`recordId`,`attachmentType`,`uri`,`label`,`capturedAt`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AttachmentEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRecordId());
        statement.bindString(3, entity.getAttachmentType());
        statement.bindString(4, entity.getUri());
        if (entity.getLabel() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getLabel());
        }
        final String _tmp = __converters.dateToTimestamp(entity.getCapturedAt());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
      }
    };
    this.__deletionAdapterOfAttachmentEntity = new EntityDeletionOrUpdateAdapter<AttachmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `attachments` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AttachmentEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfAttachmentEntity = new EntityDeletionOrUpdateAdapter<AttachmentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `attachments` SET `id` = ?,`recordId` = ?,`attachmentType` = ?,`uri` = ?,`label` = ?,`capturedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AttachmentEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRecordId());
        statement.bindString(3, entity.getAttachmentType());
        statement.bindString(4, entity.getUri());
        if (entity.getLabel() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getLabel());
        }
        final String _tmp = __converters.dateToTimestamp(entity.getCapturedAt());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        statement.bindString(7, entity.getId());
      }
    };
  }

  @Override
  public Object insertAttachment(final AttachmentEntity attachment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAttachmentEntity.insert(attachment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAttachment(final AttachmentEntity attachment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAttachmentEntity.handle(attachment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAttachment(final AttachmentEntity attachment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAttachmentEntity.handle(attachment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAttachmentById(final String id,
      final Continuation<? super AttachmentEntity> $completion) {
    final String _sql = "SELECT * FROM attachments WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AttachmentEntity>() {
      @Override
      @Nullable
      public AttachmentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
          final int _cursorIndexOfAttachmentType = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentType");
          final int _cursorIndexOfUri = CursorUtil.getColumnIndexOrThrow(_cursor, "uri");
          final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
          final int _cursorIndexOfCapturedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "capturedAt");
          final AttachmentEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRecordId;
            _tmpRecordId = _cursor.getString(_cursorIndexOfRecordId);
            final String _tmpAttachmentType;
            _tmpAttachmentType = _cursor.getString(_cursorIndexOfAttachmentType);
            final String _tmpUri;
            _tmpUri = _cursor.getString(_cursorIndexOfUri);
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            final LocalDateTime _tmpCapturedAt;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCapturedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCapturedAt);
            }
            _tmpCapturedAt = __converters.fromTimestamp(_tmp);
            _result = new AttachmentEntity(_tmpId,_tmpRecordId,_tmpAttachmentType,_tmpUri,_tmpLabel,_tmpCapturedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AttachmentEntity>> getAllAttachments() {
    final String _sql = "SELECT * FROM attachments";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"attachments"}, new Callable<List<AttachmentEntity>>() {
      @Override
      @NonNull
      public List<AttachmentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
          final int _cursorIndexOfAttachmentType = CursorUtil.getColumnIndexOrThrow(_cursor, "attachmentType");
          final int _cursorIndexOfUri = CursorUtil.getColumnIndexOrThrow(_cursor, "uri");
          final int _cursorIndexOfLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "label");
          final int _cursorIndexOfCapturedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "capturedAt");
          final List<AttachmentEntity> _result = new ArrayList<AttachmentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AttachmentEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRecordId;
            _tmpRecordId = _cursor.getString(_cursorIndexOfRecordId);
            final String _tmpAttachmentType;
            _tmpAttachmentType = _cursor.getString(_cursorIndexOfAttachmentType);
            final String _tmpUri;
            _tmpUri = _cursor.getString(_cursorIndexOfUri);
            final String _tmpLabel;
            if (_cursor.isNull(_cursorIndexOfLabel)) {
              _tmpLabel = null;
            } else {
              _tmpLabel = _cursor.getString(_cursorIndexOfLabel);
            }
            final LocalDateTime _tmpCapturedAt;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCapturedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCapturedAt);
            }
            _tmpCapturedAt = __converters.fromTimestamp(_tmp);
            _item = new AttachmentEntity(_tmpId,_tmpRecordId,_tmpAttachmentType,_tmpUri,_tmpLabel,_tmpCapturedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
