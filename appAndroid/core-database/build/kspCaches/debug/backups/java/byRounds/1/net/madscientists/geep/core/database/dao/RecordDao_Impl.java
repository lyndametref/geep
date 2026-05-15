package net.madscientists.geep.core.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
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
import net.madscientists.geep.core.database.entity.IndividualRecordCrossRef;
import net.madscientists.geep.core.database.entity.RecordEntity;
import net.madscientists.geep.core.database.util.Converters;
import net.madscientists.geep.core.model.FutureEventStatus;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RecordDao_Impl implements RecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RecordEntity> __insertionAdapterOfRecordEntity;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<IndividualRecordCrossRef> __insertionAdapterOfIndividualRecordCrossRef;

  private final EntityDeletionOrUpdateAdapter<RecordEntity> __deletionAdapterOfRecordEntity;

  private final EntityDeletionOrUpdateAdapter<RecordEntity> __updateAdapterOfRecordEntity;

  public RecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecordEntity = new EntityInsertionAdapter<RecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `records` (`id`,`timestamp`,`type`,`observationType`,`content`,`interventionType`,`status`,`sourceRecordId`,`earliestDate`,`latestDate`,`title`,`reminderDate`,`dueDate`,`delayElapsedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RecordEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.dateToTimestamp(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindString(3, entity.getType());
        if (entity.getObservationType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getObservationType());
        }
        if (entity.getContent() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContent());
        }
        if (entity.getInterventionType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getInterventionType());
        }
        final String _tmp_1 = __converters.futureEventStatusToString(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        if (entity.getSourceRecordId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getSourceRecordId());
        }
        final String _tmp_2 = __converters.dateToTimestamp(entity.getEarliestDate());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        final String _tmp_3 = __converters.dateToTimestamp(entity.getLatestDate());
        if (_tmp_3 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp_3);
        }
        if (entity.getTitle() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getTitle());
        }
        final String _tmp_4 = __converters.dateToTimestamp(entity.getReminderDate());
        if (_tmp_4 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_4);
        }
        final String _tmp_5 = __converters.dateToTimestamp(entity.getDueDate());
        if (_tmp_5 == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, _tmp_5);
        }
        final String _tmp_6 = __converters.dateToTimestamp(entity.getDelayElapsedAt());
        if (_tmp_6 == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, _tmp_6);
        }
      }
    };
    this.__insertionAdapterOfIndividualRecordCrossRef = new EntityInsertionAdapter<IndividualRecordCrossRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `individual_record_cross_ref` (`individualId`,`recordId`) VALUES (?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final IndividualRecordCrossRef entity) {
        statement.bindString(1, entity.getIndividualId());
        statement.bindString(2, entity.getRecordId());
      }
    };
    this.__deletionAdapterOfRecordEntity = new EntityDeletionOrUpdateAdapter<RecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RecordEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfRecordEntity = new EntityDeletionOrUpdateAdapter<RecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `records` SET `id` = ?,`timestamp` = ?,`type` = ?,`observationType` = ?,`content` = ?,`interventionType` = ?,`status` = ?,`sourceRecordId` = ?,`earliestDate` = ?,`latestDate` = ?,`title` = ?,`reminderDate` = ?,`dueDate` = ?,`delayElapsedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RecordEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.dateToTimestamp(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindString(3, entity.getType());
        if (entity.getObservationType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getObservationType());
        }
        if (entity.getContent() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContent());
        }
        if (entity.getInterventionType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getInterventionType());
        }
        final String _tmp_1 = __converters.futureEventStatusToString(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        if (entity.getSourceRecordId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getSourceRecordId());
        }
        final String _tmp_2 = __converters.dateToTimestamp(entity.getEarliestDate());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        final String _tmp_3 = __converters.dateToTimestamp(entity.getLatestDate());
        if (_tmp_3 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp_3);
        }
        if (entity.getTitle() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getTitle());
        }
        final String _tmp_4 = __converters.dateToTimestamp(entity.getReminderDate());
        if (_tmp_4 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_4);
        }
        final String _tmp_5 = __converters.dateToTimestamp(entity.getDueDate());
        if (_tmp_5 == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, _tmp_5);
        }
        final String _tmp_6 = __converters.dateToTimestamp(entity.getDelayElapsedAt());
        if (_tmp_6 == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, _tmp_6);
        }
        statement.bindString(15, entity.getId());
      }
    };
  }

  @Override
  public Object insertRecord(final RecordEntity record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRecordEntity.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertIndividualRecordCrossRef(final IndividualRecordCrossRef crossRef,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIndividualRecordCrossRef.insert(crossRef);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRecord(final RecordEntity record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRecordEntity.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRecord(final RecordEntity record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRecordEntity.handle(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertBatchRecord(final RecordEntity record, final List<String> individualIds,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> RecordDao.DefaultImpls.insertBatchRecord(RecordDao_Impl.this, record, individualIds, __cont), $completion);
  }

  @Override
  public Object getRecordById(final String id,
      final Continuation<? super RecordEntity> $completion) {
    final String _sql = "SELECT * FROM records WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RecordEntity>() {
      @Override
      @Nullable
      public RecordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfObservationType = CursorUtil.getColumnIndexOrThrow(_cursor, "observationType");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfInterventionType = CursorUtil.getColumnIndexOrThrow(_cursor, "interventionType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSourceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceRecordId");
          final int _cursorIndexOfEarliestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "earliestDate");
          final int _cursorIndexOfLatestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "latestDate");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfReminderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderDate");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDelayElapsedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "delayElapsedAt");
          final RecordEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final LocalDateTime _tmpTimestamp;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimestamp);
            }
            final LocalDateTime _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpTimestamp = _tmp_1;
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpObservationType;
            if (_cursor.isNull(_cursorIndexOfObservationType)) {
              _tmpObservationType = null;
            } else {
              _tmpObservationType = _cursor.getString(_cursorIndexOfObservationType);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpInterventionType;
            if (_cursor.isNull(_cursorIndexOfInterventionType)) {
              _tmpInterventionType = null;
            } else {
              _tmpInterventionType = _cursor.getString(_cursorIndexOfInterventionType);
            }
            final FutureEventStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.fromFutureEventStatus(_tmp_2);
            final String _tmpSourceRecordId;
            if (_cursor.isNull(_cursorIndexOfSourceRecordId)) {
              _tmpSourceRecordId = null;
            } else {
              _tmpSourceRecordId = _cursor.getString(_cursorIndexOfSourceRecordId);
            }
            final LocalDateTime _tmpEarliestDate;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfEarliestDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfEarliestDate);
            }
            _tmpEarliestDate = __converters.fromTimestamp(_tmp_3);
            final LocalDateTime _tmpLatestDate;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLatestDate)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfLatestDate);
            }
            _tmpLatestDate = __converters.fromTimestamp(_tmp_4);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final LocalDateTime _tmpReminderDate;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfReminderDate)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfReminderDate);
            }
            _tmpReminderDate = __converters.fromTimestamp(_tmp_5);
            final LocalDateTime _tmpDueDate;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfDueDate);
            }
            _tmpDueDate = __converters.fromTimestamp(_tmp_6);
            final LocalDateTime _tmpDelayElapsedAt;
            final String _tmp_7;
            if (_cursor.isNull(_cursorIndexOfDelayElapsedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getString(_cursorIndexOfDelayElapsedAt);
            }
            _tmpDelayElapsedAt = __converters.fromTimestamp(_tmp_7);
            _result = new RecordEntity(_tmpId,_tmpTimestamp,_tmpType,_tmpObservationType,_tmpContent,_tmpInterventionType,_tmpStatus,_tmpSourceRecordId,_tmpEarliestDate,_tmpLatestDate,_tmpTitle,_tmpReminderDate,_tmpDueDate,_tmpDelayElapsedAt);
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
  public Flow<List<RecordEntity>> getAllRecords() {
    final String _sql = "SELECT * FROM records";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"records"}, new Callable<List<RecordEntity>>() {
      @Override
      @NonNull
      public List<RecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfObservationType = CursorUtil.getColumnIndexOrThrow(_cursor, "observationType");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfInterventionType = CursorUtil.getColumnIndexOrThrow(_cursor, "interventionType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfSourceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceRecordId");
          final int _cursorIndexOfEarliestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "earliestDate");
          final int _cursorIndexOfLatestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "latestDate");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfReminderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderDate");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfDelayElapsedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "delayElapsedAt");
          final List<RecordEntity> _result = new ArrayList<RecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RecordEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final LocalDateTime _tmpTimestamp;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimestamp);
            }
            final LocalDateTime _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpTimestamp = _tmp_1;
            }
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final String _tmpObservationType;
            if (_cursor.isNull(_cursorIndexOfObservationType)) {
              _tmpObservationType = null;
            } else {
              _tmpObservationType = _cursor.getString(_cursorIndexOfObservationType);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpInterventionType;
            if (_cursor.isNull(_cursorIndexOfInterventionType)) {
              _tmpInterventionType = null;
            } else {
              _tmpInterventionType = _cursor.getString(_cursorIndexOfInterventionType);
            }
            final FutureEventStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.fromFutureEventStatus(_tmp_2);
            final String _tmpSourceRecordId;
            if (_cursor.isNull(_cursorIndexOfSourceRecordId)) {
              _tmpSourceRecordId = null;
            } else {
              _tmpSourceRecordId = _cursor.getString(_cursorIndexOfSourceRecordId);
            }
            final LocalDateTime _tmpEarliestDate;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfEarliestDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfEarliestDate);
            }
            _tmpEarliestDate = __converters.fromTimestamp(_tmp_3);
            final LocalDateTime _tmpLatestDate;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLatestDate)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfLatestDate);
            }
            _tmpLatestDate = __converters.fromTimestamp(_tmp_4);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final LocalDateTime _tmpReminderDate;
            final String _tmp_5;
            if (_cursor.isNull(_cursorIndexOfReminderDate)) {
              _tmp_5 = null;
            } else {
              _tmp_5 = _cursor.getString(_cursorIndexOfReminderDate);
            }
            _tmpReminderDate = __converters.fromTimestamp(_tmp_5);
            final LocalDateTime _tmpDueDate;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfDueDate);
            }
            _tmpDueDate = __converters.fromTimestamp(_tmp_6);
            final LocalDateTime _tmpDelayElapsedAt;
            final String _tmp_7;
            if (_cursor.isNull(_cursorIndexOfDelayElapsedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getString(_cursorIndexOfDelayElapsedAt);
            }
            _tmpDelayElapsedAt = __converters.fromTimestamp(_tmp_7);
            _item = new RecordEntity(_tmpId,_tmpTimestamp,_tmpType,_tmpObservationType,_tmpContent,_tmpInterventionType,_tmpStatus,_tmpSourceRecordId,_tmpEarliestDate,_tmpLatestDate,_tmpTitle,_tmpReminderDate,_tmpDueDate,_tmpDelayElapsedAt);
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

  @Override
  public Flow<List<IndividualRecordCrossRef>> getAllRecordAssociations() {
    final String _sql = "SELECT * FROM individual_record_cross_ref";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"individual_record_cross_ref"}, new Callable<List<IndividualRecordCrossRef>>() {
      @Override
      @NonNull
      public List<IndividualRecordCrossRef> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIndividualId = CursorUtil.getColumnIndexOrThrow(_cursor, "individualId");
          final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
          final List<IndividualRecordCrossRef> _result = new ArrayList<IndividualRecordCrossRef>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IndividualRecordCrossRef _item;
            final String _tmpIndividualId;
            _tmpIndividualId = _cursor.getString(_cursorIndexOfIndividualId);
            final String _tmpRecordId;
            _tmpRecordId = _cursor.getString(_cursorIndexOfRecordId);
            _item = new IndividualRecordCrossRef(_tmpIndividualId,_tmpRecordId);
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
