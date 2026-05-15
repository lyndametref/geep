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
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import net.madscientists.geep.core.database.entity.IndividualEntity;
import net.madscientists.geep.core.database.util.Converters;
import net.madscientists.geep.core.model.Sex;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class IndividualDao_Impl implements IndividualDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<IndividualEntity> __insertionAdapterOfIndividualEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<IndividualEntity> __deletionAdapterOfIndividualEntity;

  private final EntityDeletionOrUpdateAdapter<IndividualEntity> __updateAdapterOfIndividualEntity;

  public IndividualDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfIndividualEntity = new EntityInsertionAdapter<IndividualEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `individuals` (`id`,`name`,`bdtaNumber`,`birthDate`,`deathDate`,`sex`,`colorPattern`,`living`,`stillborn`,`portraitReference`,`sireId`,`damId`,`notes`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final IndividualEntity entity) {
        statement.bindString(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getBdtaNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBdtaNumber());
        }
        final String _tmp = __converters.localDateToTimestamp(entity.getBirthDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __converters.localDateToTimestamp(entity.getDeathDate());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final String _tmp_2 = __converters.sexToString(entity.getSex());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        if (entity.getColorPattern() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getColorPattern());
        }
        final int _tmp_3 = entity.getLiving() ? 1 : 0;
        statement.bindLong(8, _tmp_3);
        final int _tmp_4 = entity.getStillborn() ? 1 : 0;
        statement.bindLong(9, _tmp_4);
        if (entity.getPortraitReference() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getPortraitReference());
        }
        if (entity.getSireId() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getSireId());
        }
        if (entity.getDamId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getDamId());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
      }
    };
    this.__deletionAdapterOfIndividualEntity = new EntityDeletionOrUpdateAdapter<IndividualEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `individuals` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final IndividualEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfIndividualEntity = new EntityDeletionOrUpdateAdapter<IndividualEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `individuals` SET `id` = ?,`name` = ?,`bdtaNumber` = ?,`birthDate` = ?,`deathDate` = ?,`sex` = ?,`colorPattern` = ?,`living` = ?,`stillborn` = ?,`portraitReference` = ?,`sireId` = ?,`damId` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final IndividualEntity entity) {
        statement.bindString(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getBdtaNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getBdtaNumber());
        }
        final String _tmp = __converters.localDateToTimestamp(entity.getBirthDate());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __converters.localDateToTimestamp(entity.getDeathDate());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final String _tmp_2 = __converters.sexToString(entity.getSex());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        if (entity.getColorPattern() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getColorPattern());
        }
        final int _tmp_3 = entity.getLiving() ? 1 : 0;
        statement.bindLong(8, _tmp_3);
        final int _tmp_4 = entity.getStillborn() ? 1 : 0;
        statement.bindLong(9, _tmp_4);
        if (entity.getPortraitReference() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getPortraitReference());
        }
        if (entity.getSireId() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getSireId());
        }
        if (entity.getDamId() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getDamId());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        statement.bindString(14, entity.getId());
      }
    };
  }

  @Override
  public Object insertIndividual(final IndividualEntity individual,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfIndividualEntity.insert(individual);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteIndividual(final IndividualEntity individual,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfIndividualEntity.handle(individual);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateIndividual(final IndividualEntity individual,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfIndividualEntity.handle(individual);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getIndividualById(final String id,
      final Continuation<? super IndividualEntity> $completion) {
    final String _sql = "SELECT * FROM individuals WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<IndividualEntity>() {
      @Override
      @Nullable
      public IndividualEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBdtaNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "bdtaNumber");
          final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
          final int _cursorIndexOfDeathDate = CursorUtil.getColumnIndexOrThrow(_cursor, "deathDate");
          final int _cursorIndexOfSex = CursorUtil.getColumnIndexOrThrow(_cursor, "sex");
          final int _cursorIndexOfColorPattern = CursorUtil.getColumnIndexOrThrow(_cursor, "colorPattern");
          final int _cursorIndexOfLiving = CursorUtil.getColumnIndexOrThrow(_cursor, "living");
          final int _cursorIndexOfStillborn = CursorUtil.getColumnIndexOrThrow(_cursor, "stillborn");
          final int _cursorIndexOfPortraitReference = CursorUtil.getColumnIndexOrThrow(_cursor, "portraitReference");
          final int _cursorIndexOfSireId = CursorUtil.getColumnIndexOrThrow(_cursor, "sireId");
          final int _cursorIndexOfDamId = CursorUtil.getColumnIndexOrThrow(_cursor, "damId");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final IndividualEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBdtaNumber;
            if (_cursor.isNull(_cursorIndexOfBdtaNumber)) {
              _tmpBdtaNumber = null;
            } else {
              _tmpBdtaNumber = _cursor.getString(_cursorIndexOfBdtaNumber);
            }
            final LocalDate _tmpBirthDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfBirthDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfBirthDate);
            }
            final LocalDate _tmp_1 = __converters.fromLocalDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpBirthDate = _tmp_1;
            }
            final LocalDate _tmpDeathDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDeathDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfDeathDate);
            }
            _tmpDeathDate = __converters.fromLocalDate(_tmp_2);
            final Sex _tmpSex;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfSex)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfSex);
            }
            final Sex _tmp_4 = __converters.fromSex(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'net.madscientists.geep.core.model.Sex', but it was NULL.");
            } else {
              _tmpSex = _tmp_4;
            }
            final String _tmpColorPattern;
            if (_cursor.isNull(_cursorIndexOfColorPattern)) {
              _tmpColorPattern = null;
            } else {
              _tmpColorPattern = _cursor.getString(_cursorIndexOfColorPattern);
            }
            final boolean _tmpLiving;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfLiving);
            _tmpLiving = _tmp_5 != 0;
            final boolean _tmpStillborn;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfStillborn);
            _tmpStillborn = _tmp_6 != 0;
            final String _tmpPortraitReference;
            if (_cursor.isNull(_cursorIndexOfPortraitReference)) {
              _tmpPortraitReference = null;
            } else {
              _tmpPortraitReference = _cursor.getString(_cursorIndexOfPortraitReference);
            }
            final String _tmpSireId;
            if (_cursor.isNull(_cursorIndexOfSireId)) {
              _tmpSireId = null;
            } else {
              _tmpSireId = _cursor.getString(_cursorIndexOfSireId);
            }
            final String _tmpDamId;
            if (_cursor.isNull(_cursorIndexOfDamId)) {
              _tmpDamId = null;
            } else {
              _tmpDamId = _cursor.getString(_cursorIndexOfDamId);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new IndividualEntity(_tmpId,_tmpName,_tmpBdtaNumber,_tmpBirthDate,_tmpDeathDate,_tmpSex,_tmpColorPattern,_tmpLiving,_tmpStillborn,_tmpPortraitReference,_tmpSireId,_tmpDamId,_tmpNotes);
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
  public Flow<List<IndividualEntity>> getAllIndividuals() {
    final String _sql = "SELECT * FROM individuals";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"individuals"}, new Callable<List<IndividualEntity>>() {
      @Override
      @NonNull
      public List<IndividualEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfBdtaNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "bdtaNumber");
          final int _cursorIndexOfBirthDate = CursorUtil.getColumnIndexOrThrow(_cursor, "birthDate");
          final int _cursorIndexOfDeathDate = CursorUtil.getColumnIndexOrThrow(_cursor, "deathDate");
          final int _cursorIndexOfSex = CursorUtil.getColumnIndexOrThrow(_cursor, "sex");
          final int _cursorIndexOfColorPattern = CursorUtil.getColumnIndexOrThrow(_cursor, "colorPattern");
          final int _cursorIndexOfLiving = CursorUtil.getColumnIndexOrThrow(_cursor, "living");
          final int _cursorIndexOfStillborn = CursorUtil.getColumnIndexOrThrow(_cursor, "stillborn");
          final int _cursorIndexOfPortraitReference = CursorUtil.getColumnIndexOrThrow(_cursor, "portraitReference");
          final int _cursorIndexOfSireId = CursorUtil.getColumnIndexOrThrow(_cursor, "sireId");
          final int _cursorIndexOfDamId = CursorUtil.getColumnIndexOrThrow(_cursor, "damId");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<IndividualEntity> _result = new ArrayList<IndividualEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final IndividualEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpBdtaNumber;
            if (_cursor.isNull(_cursorIndexOfBdtaNumber)) {
              _tmpBdtaNumber = null;
            } else {
              _tmpBdtaNumber = _cursor.getString(_cursorIndexOfBdtaNumber);
            }
            final LocalDate _tmpBirthDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfBirthDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfBirthDate);
            }
            final LocalDate _tmp_1 = __converters.fromLocalDate(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpBirthDate = _tmp_1;
            }
            final LocalDate _tmpDeathDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDeathDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfDeathDate);
            }
            _tmpDeathDate = __converters.fromLocalDate(_tmp_2);
            final Sex _tmpSex;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfSex)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfSex);
            }
            final Sex _tmp_4 = __converters.fromSex(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'net.madscientists.geep.core.model.Sex', but it was NULL.");
            } else {
              _tmpSex = _tmp_4;
            }
            final String _tmpColorPattern;
            if (_cursor.isNull(_cursorIndexOfColorPattern)) {
              _tmpColorPattern = null;
            } else {
              _tmpColorPattern = _cursor.getString(_cursorIndexOfColorPattern);
            }
            final boolean _tmpLiving;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfLiving);
            _tmpLiving = _tmp_5 != 0;
            final boolean _tmpStillborn;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfStillborn);
            _tmpStillborn = _tmp_6 != 0;
            final String _tmpPortraitReference;
            if (_cursor.isNull(_cursorIndexOfPortraitReference)) {
              _tmpPortraitReference = null;
            } else {
              _tmpPortraitReference = _cursor.getString(_cursorIndexOfPortraitReference);
            }
            final String _tmpSireId;
            if (_cursor.isNull(_cursorIndexOfSireId)) {
              _tmpSireId = null;
            } else {
              _tmpSireId = _cursor.getString(_cursorIndexOfSireId);
            }
            final String _tmpDamId;
            if (_cursor.isNull(_cursorIndexOfDamId)) {
              _tmpDamId = null;
            } else {
              _tmpDamId = _cursor.getString(_cursorIndexOfDamId);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new IndividualEntity(_tmpId,_tmpName,_tmpBdtaNumber,_tmpBirthDate,_tmpDeathDate,_tmpSex,_tmpColorPattern,_tmpLiving,_tmpStillborn,_tmpPortraitReference,_tmpSireId,_tmpDamId,_tmpNotes);
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
