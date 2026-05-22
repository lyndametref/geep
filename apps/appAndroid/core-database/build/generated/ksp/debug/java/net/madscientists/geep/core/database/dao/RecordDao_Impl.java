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
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import net.madscientists.geep.core.database.entity.FutureEventEntity;
import net.madscientists.geep.core.database.entity.InterventionEntity;
import net.madscientists.geep.core.database.entity.ObservationEntity;
import net.madscientists.geep.core.database.entity.PlannedTaskEntity;
import net.madscientists.geep.core.database.entity.PredictedEventEntity;
import net.madscientists.geep.core.database.entity.RecordEntity;
import net.madscientists.geep.core.database.entity.WaitingDelayEntity;
import net.madscientists.geep.core.database.util.Converters;
import net.madscientists.geep.core.model.DelayStatus;
import net.madscientists.geep.core.model.PredictionStatus;
import net.madscientists.geep.core.model.TaskStatus;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RecordDao_Impl implements RecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RecordEntity> __insertionAdapterOfRecordEntity;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<ObservationEntity> __insertionAdapterOfObservationEntity;

  private final EntityInsertionAdapter<InterventionEntity> __insertionAdapterOfInterventionEntity;

  private final EntityInsertionAdapter<FutureEventEntity> __insertionAdapterOfFutureEventEntity;

  private final EntityInsertionAdapter<PredictedEventEntity> __insertionAdapterOfPredictedEventEntity;

  private final EntityInsertionAdapter<PlannedTaskEntity> __insertionAdapterOfPlannedTaskEntity;

  private final EntityInsertionAdapter<WaitingDelayEntity> __insertionAdapterOfWaitingDelayEntity;

  private final EntityDeletionOrUpdateAdapter<RecordEntity> __deletionAdapterOfRecordEntity;

  private final EntityDeletionOrUpdateAdapter<RecordEntity> __updateAdapterOfRecordEntity;

  public RecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecordEntity = new EntityInsertionAdapter<RecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `records` (`id`,`timestamp`,`recordType`,`individualId`,`sourceRecordId`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RecordEntity entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __converters.instantToString(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindString(3, entity.getRecordType());
        if (entity.getIndividualId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getIndividualId());
        }
        if (entity.getSourceRecordId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getSourceRecordId());
        }
      }
    };
    this.__insertionAdapterOfObservationEntity = new EntityInsertionAdapter<ObservationEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `observations` (`recordId`,`observedAt`,`content`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ObservationEntity entity) {
        statement.bindLong(1, entity.getRecordId());
        final String _tmp = __converters.instantToString(entity.getObservedAt());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindString(3, entity.getContent());
      }
    };
    this.__insertionAdapterOfInterventionEntity = new EntityInsertionAdapter<InterventionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `interventions` (`recordId`,`performedAt`,`content`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InterventionEntity entity) {
        statement.bindLong(1, entity.getRecordId());
        final String _tmp = __converters.instantToString(entity.getPerformedAt());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindString(3, entity.getContent());
      }
    };
    this.__insertionAdapterOfFutureEventEntity = new EntityInsertionAdapter<FutureEventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `future_events` (`recordId`) VALUES (?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FutureEventEntity entity) {
        statement.bindLong(1, entity.getRecordId());
      }
    };
    this.__insertionAdapterOfPredictedEventEntity = new EntityInsertionAdapter<PredictedEventEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `predicted_events` (`futureEventId`,`status`,`earliestDate`,`latestDate`,`content`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PredictedEventEntity entity) {
        statement.bindLong(1, entity.getFutureEventId());
        final String _tmp = __converters.predictionStatusToString(entity.getStatus());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        final String _tmp_1 = __converters.instantToString(entity.getEarliestDate());
        if (_tmp_1 == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp_1);
        }
        final String _tmp_2 = __converters.instantToString(entity.getLatestDate());
        if (_tmp_2 == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp_2);
        }
        if (entity.getContent() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContent());
        }
      }
    };
    this.__insertionAdapterOfPlannedTaskEntity = new EntityInsertionAdapter<PlannedTaskEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `planned_tasks` (`futureEventId`,`status`,`reminderDate`,`dueDate`,`content`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlannedTaskEntity entity) {
        statement.bindLong(1, entity.getFutureEventId());
        final String _tmp = __converters.taskStatusToString(entity.getStatus());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        final String _tmp_1 = __converters.instantToString(entity.getReminderDate());
        if (_tmp_1 == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp_1);
        }
        final String _tmp_2 = __converters.instantToString(entity.getDueDate());
        if (_tmp_2 == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp_2);
        }
        statement.bindString(5, entity.getContent());
      }
    };
    this.__insertionAdapterOfWaitingDelayEntity = new EntityInsertionAdapter<WaitingDelayEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `waiting_delays` (`futureEventId`,`status`,`title`,`delayElapsedAt`,`content`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WaitingDelayEntity entity) {
        statement.bindLong(1, entity.getFutureEventId());
        final String _tmp = __converters.delayStatusToString(entity.getStatus());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindString(3, entity.getTitle());
        final String _tmp_1 = __converters.instantToString(entity.getDelayElapsedAt());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp_1);
        }
        if (entity.getContent() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getContent());
        }
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
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRecordEntity = new EntityDeletionOrUpdateAdapter<RecordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `records` SET `id` = ?,`timestamp` = ?,`recordType` = ?,`individualId` = ?,`sourceRecordId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RecordEntity entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __converters.instantToString(entity.getTimestamp());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        statement.bindString(3, entity.getRecordType());
        if (entity.getIndividualId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getIndividualId());
        }
        if (entity.getSourceRecordId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getSourceRecordId());
        }
        statement.bindLong(6, entity.getId());
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
  public Object insertObservation(final ObservationEntity observation,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfObservationEntity.insert(observation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertIntervention(final InterventionEntity intervention,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfInterventionEntity.insert(intervention);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertFutureEvent(final FutureEventEntity futureEvent,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFutureEventEntity.insert(futureEvent);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPredictedEvent(final PredictedEventEntity predictedEvent,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPredictedEventEntity.insert(predictedEvent);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPlannedTask(final PlannedTaskEntity plannedTask,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlannedTaskEntity.insert(plannedTask);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertWaitingDelay(final WaitingDelayEntity waitingDelay,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWaitingDelayEntity.insert(waitingDelay);
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
  public Object insertObservationRecord(final long recordId, final Instant timestamp,
      final Long individualId, final Long sourceRecordId, final ObservationEntity observation,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> RecordDao.DefaultImpls.insertObservationRecord(RecordDao_Impl.this, recordId, timestamp, individualId, sourceRecordId, observation, __cont), $completion);
  }

  @Override
  public Object insertInterventionRecord(final long recordId, final Instant timestamp,
      final Long individualId, final Long sourceRecordId, final InterventionEntity intervention,
      final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> RecordDao.DefaultImpls.insertInterventionRecord(RecordDao_Impl.this, recordId, timestamp, individualId, sourceRecordId, intervention, __cont), $completion);
  }

  @Override
  public Object insertPredictedEventRecord(final long recordId, final Instant timestamp,
      final Long individualId, final Long sourceRecordId, final FutureEventEntity futureEvent,
      final PredictedEventEntity predictedEvent, final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> RecordDao.DefaultImpls.insertPredictedEventRecord(RecordDao_Impl.this, recordId, timestamp, individualId, sourceRecordId, futureEvent, predictedEvent, __cont), $completion);
  }

  @Override
  public Object insertPlannedTaskRecord(final long recordId, final Instant timestamp,
      final Long individualId, final Long sourceRecordId, final FutureEventEntity futureEvent,
      final PlannedTaskEntity plannedTask, final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> RecordDao.DefaultImpls.insertPlannedTaskRecord(RecordDao_Impl.this, recordId, timestamp, individualId, sourceRecordId, futureEvent, plannedTask, __cont), $completion);
  }

  @Override
  public Object insertWaitingDelayRecord(final long recordId, final Instant timestamp,
      final Long individualId, final Long sourceRecordId, final FutureEventEntity futureEvent,
      final WaitingDelayEntity waitingDelay, final Continuation<? super Unit> $completion) {
    return RoomDatabaseKt.withTransaction(__db, (__cont) -> RecordDao.DefaultImpls.insertWaitingDelayRecord(RecordDao_Impl.this, recordId, timestamp, individualId, sourceRecordId, futureEvent, waitingDelay, __cont), $completion);
  }

  @Override
  public Object getRecordById(final long id, final Continuation<? super RecordEntity> $completion) {
    final String _sql = "SELECT * FROM records WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RecordEntity>() {
      @Override
      @Nullable
      public RecordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfRecordType = CursorUtil.getColumnIndexOrThrow(_cursor, "recordType");
          final int _cursorIndexOfIndividualId = CursorUtil.getColumnIndexOrThrow(_cursor, "individualId");
          final int _cursorIndexOfSourceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceRecordId");
          final RecordEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Instant _tmpTimestamp;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimestamp);
            }
            final Instant _tmp_1 = __converters.fromInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpTimestamp = _tmp_1;
            }
            final String _tmpRecordType;
            _tmpRecordType = _cursor.getString(_cursorIndexOfRecordType);
            final Long _tmpIndividualId;
            if (_cursor.isNull(_cursorIndexOfIndividualId)) {
              _tmpIndividualId = null;
            } else {
              _tmpIndividualId = _cursor.getLong(_cursorIndexOfIndividualId);
            }
            final Long _tmpSourceRecordId;
            if (_cursor.isNull(_cursorIndexOfSourceRecordId)) {
              _tmpSourceRecordId = null;
            } else {
              _tmpSourceRecordId = _cursor.getLong(_cursorIndexOfSourceRecordId);
            }
            _result = new RecordEntity(_tmpId,_tmpTimestamp,_tmpRecordType,_tmpIndividualId,_tmpSourceRecordId);
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
          final int _cursorIndexOfRecordType = CursorUtil.getColumnIndexOrThrow(_cursor, "recordType");
          final int _cursorIndexOfIndividualId = CursorUtil.getColumnIndexOrThrow(_cursor, "individualId");
          final int _cursorIndexOfSourceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceRecordId");
          final List<RecordEntity> _result = new ArrayList<RecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RecordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Instant _tmpTimestamp;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimestamp);
            }
            final Instant _tmp_1 = __converters.fromInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpTimestamp = _tmp_1;
            }
            final String _tmpRecordType;
            _tmpRecordType = _cursor.getString(_cursorIndexOfRecordType);
            final Long _tmpIndividualId;
            if (_cursor.isNull(_cursorIndexOfIndividualId)) {
              _tmpIndividualId = null;
            } else {
              _tmpIndividualId = _cursor.getLong(_cursorIndexOfIndividualId);
            }
            final Long _tmpSourceRecordId;
            if (_cursor.isNull(_cursorIndexOfSourceRecordId)) {
              _tmpSourceRecordId = null;
            } else {
              _tmpSourceRecordId = _cursor.getLong(_cursorIndexOfSourceRecordId);
            }
            _item = new RecordEntity(_tmpId,_tmpTimestamp,_tmpRecordType,_tmpIndividualId,_tmpSourceRecordId);
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
  public Flow<List<RecordEntity>> getRecordsByIndividualId(final long individualId) {
    final String _sql = "SELECT * FROM records WHERE individualId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, individualId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"records"}, new Callable<List<RecordEntity>>() {
      @Override
      @NonNull
      public List<RecordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfRecordType = CursorUtil.getColumnIndexOrThrow(_cursor, "recordType");
          final int _cursorIndexOfIndividualId = CursorUtil.getColumnIndexOrThrow(_cursor, "individualId");
          final int _cursorIndexOfSourceRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceRecordId");
          final List<RecordEntity> _result = new ArrayList<RecordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RecordEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final Instant _tmpTimestamp;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimestamp);
            }
            final Instant _tmp_1 = __converters.fromInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpTimestamp = _tmp_1;
            }
            final String _tmpRecordType;
            _tmpRecordType = _cursor.getString(_cursorIndexOfRecordType);
            final Long _tmpIndividualId;
            if (_cursor.isNull(_cursorIndexOfIndividualId)) {
              _tmpIndividualId = null;
            } else {
              _tmpIndividualId = _cursor.getLong(_cursorIndexOfIndividualId);
            }
            final Long _tmpSourceRecordId;
            if (_cursor.isNull(_cursorIndexOfSourceRecordId)) {
              _tmpSourceRecordId = null;
            } else {
              _tmpSourceRecordId = _cursor.getLong(_cursorIndexOfSourceRecordId);
            }
            _item = new RecordEntity(_tmpId,_tmpTimestamp,_tmpRecordType,_tmpIndividualId,_tmpSourceRecordId);
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
  public Object getObservationByRecordId(final long recordId,
      final Continuation<? super ObservationEntity> $completion) {
    final String _sql = "SELECT * FROM observations WHERE recordId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, recordId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ObservationEntity>() {
      @Override
      @Nullable
      public ObservationEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
          final int _cursorIndexOfObservedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "observedAt");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final ObservationEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpRecordId;
            _tmpRecordId = _cursor.getLong(_cursorIndexOfRecordId);
            final Instant _tmpObservedAt;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfObservedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfObservedAt);
            }
            final Instant _tmp_1 = __converters.fromInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpObservedAt = _tmp_1;
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            _result = new ObservationEntity(_tmpRecordId,_tmpObservedAt,_tmpContent);
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
  public Object getInterventionByRecordId(final long recordId,
      final Continuation<? super InterventionEntity> $completion) {
    final String _sql = "SELECT * FROM interventions WHERE recordId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, recordId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<InterventionEntity>() {
      @Override
      @Nullable
      public InterventionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
          final int _cursorIndexOfPerformedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "performedAt");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final InterventionEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpRecordId;
            _tmpRecordId = _cursor.getLong(_cursorIndexOfRecordId);
            final Instant _tmpPerformedAt;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfPerformedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfPerformedAt);
            }
            final Instant _tmp_1 = __converters.fromInstant(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpPerformedAt = _tmp_1;
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            _result = new InterventionEntity(_tmpRecordId,_tmpPerformedAt,_tmpContent);
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
  public Object getFutureEventByRecordId(final long recordId,
      final Continuation<? super FutureEventEntity> $completion) {
    final String _sql = "SELECT * FROM future_events WHERE recordId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, recordId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FutureEventEntity>() {
      @Override
      @Nullable
      public FutureEventEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfRecordId = CursorUtil.getColumnIndexOrThrow(_cursor, "recordId");
          final FutureEventEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpRecordId;
            _tmpRecordId = _cursor.getLong(_cursorIndexOfRecordId);
            _result = new FutureEventEntity(_tmpRecordId);
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
  public Object getPredictedEventByFutureEventId(final long futureEventId,
      final Continuation<? super PredictedEventEntity> $completion) {
    final String _sql = "SELECT * FROM predicted_events WHERE futureEventId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, futureEventId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PredictedEventEntity>() {
      @Override
      @Nullable
      public PredictedEventEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFutureEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "futureEventId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfEarliestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "earliestDate");
          final int _cursorIndexOfLatestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "latestDate");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final PredictedEventEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpFutureEventId;
            _tmpFutureEventId = _cursor.getLong(_cursorIndexOfFutureEventId);
            final PredictionStatus _tmpStatus;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.fromPredictionStatus(_tmp);
            final Instant _tmpEarliestDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfEarliestDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfEarliestDate);
            }
            final Instant _tmp_2 = __converters.fromInstant(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpEarliestDate = _tmp_2;
            }
            final Instant _tmpLatestDate;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLatestDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLatestDate);
            }
            final Instant _tmp_4 = __converters.fromInstant(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpLatestDate = _tmp_4;
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _result = new PredictedEventEntity(_tmpFutureEventId,_tmpStatus,_tmpEarliestDate,_tmpLatestDate,_tmpContent);
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
  public Object getPlannedTaskByFutureEventId(final long futureEventId,
      final Continuation<? super PlannedTaskEntity> $completion) {
    final String _sql = "SELECT * FROM planned_tasks WHERE futureEventId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, futureEventId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PlannedTaskEntity>() {
      @Override
      @Nullable
      public PlannedTaskEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFutureEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "futureEventId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfReminderDate = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderDate");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final PlannedTaskEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpFutureEventId;
            _tmpFutureEventId = _cursor.getLong(_cursorIndexOfFutureEventId);
            final TaskStatus _tmpStatus;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.fromTaskStatus(_tmp);
            final Instant _tmpReminderDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfReminderDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfReminderDate);
            }
            final Instant _tmp_2 = __converters.fromInstant(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpReminderDate = _tmp_2;
            }
            final Instant _tmpDueDate;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfDueDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfDueDate);
            }
            final Instant _tmp_4 = __converters.fromInstant(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpDueDate = _tmp_4;
            }
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            _result = new PlannedTaskEntity(_tmpFutureEventId,_tmpStatus,_tmpReminderDate,_tmpDueDate,_tmpContent);
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
  public Object getWaitingDelayByFutureEventId(final long futureEventId,
      final Continuation<? super WaitingDelayEntity> $completion) {
    final String _sql = "SELECT * FROM waiting_delays WHERE futureEventId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, futureEventId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WaitingDelayEntity>() {
      @Override
      @Nullable
      public WaitingDelayEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfFutureEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "futureEventId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDelayElapsedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "delayElapsedAt");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final WaitingDelayEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpFutureEventId;
            _tmpFutureEventId = _cursor.getLong(_cursorIndexOfFutureEventId);
            final DelayStatus _tmpStatus;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.fromDelayStatus(_tmp);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final Instant _tmpDelayElapsedAt;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDelayElapsedAt)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDelayElapsedAt);
            }
            final Instant _tmp_2 = __converters.fromInstant(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.Instant', but it was NULL.");
            } else {
              _tmpDelayElapsedAt = _tmp_2;
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            _result = new WaitingDelayEntity(_tmpFutureEventId,_tmpStatus,_tmpTitle,_tmpDelayElapsedAt,_tmpContent);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
