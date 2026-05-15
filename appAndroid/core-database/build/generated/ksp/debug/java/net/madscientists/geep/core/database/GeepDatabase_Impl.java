package net.madscientists.geep.core.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import net.madscientists.geep.core.database.dao.AttachmentDao;
import net.madscientists.geep.core.database.dao.AttachmentDao_Impl;
import net.madscientists.geep.core.database.dao.IndividualDao;
import net.madscientists.geep.core.database.dao.IndividualDao_Impl;
import net.madscientists.geep.core.database.dao.RecordDao;
import net.madscientists.geep.core.database.dao.RecordDao_Impl;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GeepDatabase_Impl extends GeepDatabase {
  private volatile IndividualDao _individualDao;

  private volatile RecordDao _recordDao;

  private volatile AttachmentDao _attachmentDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `individuals` (`id` TEXT NOT NULL, `name` TEXT, `bdtaNumber` TEXT, `birthDate` TEXT NOT NULL, `deathDate` TEXT, `sex` TEXT NOT NULL, `colorPattern` TEXT, `living` INTEGER NOT NULL, `stillborn` INTEGER NOT NULL, `portraitReference` TEXT, `sireId` TEXT, `damId` TEXT, `notes` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `records` (`id` TEXT NOT NULL, `timestamp` TEXT NOT NULL, `type` TEXT NOT NULL, `observationType` TEXT, `content` TEXT, `interventionType` TEXT, `status` TEXT, `sourceRecordId` TEXT, `earliestDate` TEXT, `latestDate` TEXT, `title` TEXT, `reminderDate` TEXT, `dueDate` TEXT, `delayElapsedAt` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `attachments` (`id` TEXT NOT NULL, `recordId` TEXT NOT NULL, `attachmentType` TEXT NOT NULL, `uri` TEXT NOT NULL, `label` TEXT, `capturedAt` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`recordId`) REFERENCES `records`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_attachments_recordId` ON `attachments` (`recordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `individual_record_cross_ref` (`individualId` TEXT NOT NULL, `recordId` TEXT NOT NULL, PRIMARY KEY(`individualId`, `recordId`), FOREIGN KEY(`individualId`) REFERENCES `individuals`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`recordId`) REFERENCES `records`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_individual_record_cross_ref_individualId` ON `individual_record_cross_ref` (`individualId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_individual_record_cross_ref_recordId` ON `individual_record_cross_ref` (`recordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7ac709e918b661c8fb19668e278f6e53')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `individuals`");
        db.execSQL("DROP TABLE IF EXISTS `records`");
        db.execSQL("DROP TABLE IF EXISTS `attachments`");
        db.execSQL("DROP TABLE IF EXISTS `individual_record_cross_ref`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsIndividuals = new HashMap<String, TableInfo.Column>(13);
        _columnsIndividuals.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("bdtaNumber", new TableInfo.Column("bdtaNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("birthDate", new TableInfo.Column("birthDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("deathDate", new TableInfo.Column("deathDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("sex", new TableInfo.Column("sex", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("colorPattern", new TableInfo.Column("colorPattern", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("living", new TableInfo.Column("living", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("stillborn", new TableInfo.Column("stillborn", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("portraitReference", new TableInfo.Column("portraitReference", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("sireId", new TableInfo.Column("sireId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("damId", new TableInfo.Column("damId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIndividuals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIndividuals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIndividuals = new TableInfo("individuals", _columnsIndividuals, _foreignKeysIndividuals, _indicesIndividuals);
        final TableInfo _existingIndividuals = TableInfo.read(db, "individuals");
        if (!_infoIndividuals.equals(_existingIndividuals)) {
          return new RoomOpenHelper.ValidationResult(false, "individuals(net.madscientists.geep.core.database.entity.IndividualEntity).\n"
                  + " Expected:\n" + _infoIndividuals + "\n"
                  + " Found:\n" + _existingIndividuals);
        }
        final HashMap<String, TableInfo.Column> _columnsRecords = new HashMap<String, TableInfo.Column>(14);
        _columnsRecords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("timestamp", new TableInfo.Column("timestamp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("observationType", new TableInfo.Column("observationType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("interventionType", new TableInfo.Column("interventionType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("sourceRecordId", new TableInfo.Column("sourceRecordId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("earliestDate", new TableInfo.Column("earliestDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("latestDate", new TableInfo.Column("latestDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("reminderDate", new TableInfo.Column("reminderDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("dueDate", new TableInfo.Column("dueDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("delayElapsedAt", new TableInfo.Column("delayElapsedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRecords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRecords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRecords = new TableInfo("records", _columnsRecords, _foreignKeysRecords, _indicesRecords);
        final TableInfo _existingRecords = TableInfo.read(db, "records");
        if (!_infoRecords.equals(_existingRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "records(net.madscientists.geep.core.database.entity.RecordEntity).\n"
                  + " Expected:\n" + _infoRecords + "\n"
                  + " Found:\n" + _existingRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsAttachments = new HashMap<String, TableInfo.Column>(6);
        _columnsAttachments.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("recordId", new TableInfo.Column("recordId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("attachmentType", new TableInfo.Column("attachmentType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("uri", new TableInfo.Column("uri", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("label", new TableInfo.Column("label", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("capturedAt", new TableInfo.Column("capturedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttachments = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysAttachments.add(new TableInfo.ForeignKey("records", "CASCADE", "NO ACTION", Arrays.asList("recordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesAttachments = new HashSet<TableInfo.Index>(1);
        _indicesAttachments.add(new TableInfo.Index("index_attachments_recordId", false, Arrays.asList("recordId"), Arrays.asList("ASC")));
        final TableInfo _infoAttachments = new TableInfo("attachments", _columnsAttachments, _foreignKeysAttachments, _indicesAttachments);
        final TableInfo _existingAttachments = TableInfo.read(db, "attachments");
        if (!_infoAttachments.equals(_existingAttachments)) {
          return new RoomOpenHelper.ValidationResult(false, "attachments(net.madscientists.geep.core.database.entity.AttachmentEntity).\n"
                  + " Expected:\n" + _infoAttachments + "\n"
                  + " Found:\n" + _existingAttachments);
        }
        final HashMap<String, TableInfo.Column> _columnsIndividualRecordCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsIndividualRecordCrossRef.put("individualId", new TableInfo.Column("individualId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividualRecordCrossRef.put("recordId", new TableInfo.Column("recordId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIndividualRecordCrossRef = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysIndividualRecordCrossRef.add(new TableInfo.ForeignKey("individuals", "CASCADE", "NO ACTION", Arrays.asList("individualId"), Arrays.asList("id")));
        _foreignKeysIndividualRecordCrossRef.add(new TableInfo.ForeignKey("records", "CASCADE", "NO ACTION", Arrays.asList("recordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesIndividualRecordCrossRef = new HashSet<TableInfo.Index>(2);
        _indicesIndividualRecordCrossRef.add(new TableInfo.Index("index_individual_record_cross_ref_individualId", false, Arrays.asList("individualId"), Arrays.asList("ASC")));
        _indicesIndividualRecordCrossRef.add(new TableInfo.Index("index_individual_record_cross_ref_recordId", false, Arrays.asList("recordId"), Arrays.asList("ASC")));
        final TableInfo _infoIndividualRecordCrossRef = new TableInfo("individual_record_cross_ref", _columnsIndividualRecordCrossRef, _foreignKeysIndividualRecordCrossRef, _indicesIndividualRecordCrossRef);
        final TableInfo _existingIndividualRecordCrossRef = TableInfo.read(db, "individual_record_cross_ref");
        if (!_infoIndividualRecordCrossRef.equals(_existingIndividualRecordCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "individual_record_cross_ref(net.madscientists.geep.core.database.entity.IndividualRecordCrossRef).\n"
                  + " Expected:\n" + _infoIndividualRecordCrossRef + "\n"
                  + " Found:\n" + _existingIndividualRecordCrossRef);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7ac709e918b661c8fb19668e278f6e53", "46ceabf2cd338a93a45be405c6ff5f56");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "individuals","records","attachments","individual_record_cross_ref");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `individuals`");
      _db.execSQL("DELETE FROM `records`");
      _db.execSQL("DELETE FROM `attachments`");
      _db.execSQL("DELETE FROM `individual_record_cross_ref`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(IndividualDao.class, IndividualDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RecordDao.class, RecordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AttachmentDao.class, AttachmentDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    _autoMigrations.add(new GeepDatabase_AutoMigration_1_2_Impl());
    return _autoMigrations;
  }

  @Override
  public IndividualDao individualDao() {
    if (_individualDao != null) {
      return _individualDao;
    } else {
      synchronized(this) {
        if(_individualDao == null) {
          _individualDao = new IndividualDao_Impl(this);
        }
        return _individualDao;
      }
    }
  }

  @Override
  public RecordDao recordDao() {
    if (_recordDao != null) {
      return _recordDao;
    } else {
      synchronized(this) {
        if(_recordDao == null) {
          _recordDao = new RecordDao_Impl(this);
        }
        return _recordDao;
      }
    }
  }

  @Override
  public AttachmentDao attachmentDao() {
    if (_attachmentDao != null) {
      return _attachmentDao;
    } else {
      synchronized(this) {
        if(_attachmentDao == null) {
          _attachmentDao = new AttachmentDao_Impl(this);
        }
        return _attachmentDao;
      }
    }
  }
}
