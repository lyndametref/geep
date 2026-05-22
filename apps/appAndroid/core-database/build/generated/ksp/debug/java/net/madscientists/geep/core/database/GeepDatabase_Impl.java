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
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `individuals` (`id` INTEGER NOT NULL, `name` TEXT, `earTagId` TEXT, `birthDate` TEXT NOT NULL, `deathDate` TEXT, `sex` TEXT NOT NULL, `colorPattern` TEXT, `living` INTEGER NOT NULL, `stillborn` INTEGER NOT NULL, `belongsToFlock` INTEGER NOT NULL, `sireId` INTEGER, `damId` INTEGER, `notes` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `records` (`id` INTEGER NOT NULL, `timestamp` TEXT NOT NULL, `recordType` TEXT NOT NULL, `individualId` INTEGER, `sourceRecordId` INTEGER, PRIMARY KEY(`id`), FOREIGN KEY(`individualId`) REFERENCES `individuals`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_records_individualId` ON `records` (`individualId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `observations` (`recordId` INTEGER NOT NULL, `observedAt` TEXT NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`recordId`), FOREIGN KEY(`recordId`) REFERENCES `records`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_observations_recordId` ON `observations` (`recordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `interventions` (`recordId` INTEGER NOT NULL, `performedAt` TEXT NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`recordId`), FOREIGN KEY(`recordId`) REFERENCES `records`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_interventions_recordId` ON `interventions` (`recordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `future_events` (`recordId` INTEGER NOT NULL, PRIMARY KEY(`recordId`), FOREIGN KEY(`recordId`) REFERENCES `records`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_future_events_recordId` ON `future_events` (`recordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `predicted_events` (`futureEventId` INTEGER NOT NULL, `status` TEXT, `earliestDate` TEXT NOT NULL, `latestDate` TEXT NOT NULL, `content` TEXT, PRIMARY KEY(`futureEventId`), FOREIGN KEY(`futureEventId`) REFERENCES `future_events`(`recordId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_predicted_events_futureEventId` ON `predicted_events` (`futureEventId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `planned_tasks` (`futureEventId` INTEGER NOT NULL, `status` TEXT, `reminderDate` TEXT NOT NULL, `dueDate` TEXT NOT NULL, `content` TEXT NOT NULL, PRIMARY KEY(`futureEventId`), FOREIGN KEY(`futureEventId`) REFERENCES `future_events`(`recordId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_planned_tasks_futureEventId` ON `planned_tasks` (`futureEventId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `waiting_delays` (`futureEventId` INTEGER NOT NULL, `status` TEXT, `title` TEXT NOT NULL, `delayElapsedAt` TEXT NOT NULL, `content` TEXT, PRIMARY KEY(`futureEventId`), FOREIGN KEY(`futureEventId`) REFERENCES `future_events`(`recordId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_waiting_delays_futureEventId` ON `waiting_delays` (`futureEventId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `attachments` (`id` INTEGER NOT NULL, `recordId` INTEGER NOT NULL, `attachmentType` TEXT NOT NULL, `uri` TEXT NOT NULL, `label` TEXT, `capturedAt` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`recordId`) REFERENCES `records`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_attachments_recordId` ON `attachments` (`recordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'ee694efb61e3eb092f0a900633eaa110')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `individuals`");
        db.execSQL("DROP TABLE IF EXISTS `records`");
        db.execSQL("DROP TABLE IF EXISTS `observations`");
        db.execSQL("DROP TABLE IF EXISTS `interventions`");
        db.execSQL("DROP TABLE IF EXISTS `future_events`");
        db.execSQL("DROP TABLE IF EXISTS `predicted_events`");
        db.execSQL("DROP TABLE IF EXISTS `planned_tasks`");
        db.execSQL("DROP TABLE IF EXISTS `waiting_delays`");
        db.execSQL("DROP TABLE IF EXISTS `attachments`");
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
        _columnsIndividuals.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("earTagId", new TableInfo.Column("earTagId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("birthDate", new TableInfo.Column("birthDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("deathDate", new TableInfo.Column("deathDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("sex", new TableInfo.Column("sex", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("colorPattern", new TableInfo.Column("colorPattern", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("living", new TableInfo.Column("living", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("stillborn", new TableInfo.Column("stillborn", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("belongsToFlock", new TableInfo.Column("belongsToFlock", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("sireId", new TableInfo.Column("sireId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIndividuals.put("damId", new TableInfo.Column("damId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
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
        final HashMap<String, TableInfo.Column> _columnsRecords = new HashMap<String, TableInfo.Column>(5);
        _columnsRecords.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("timestamp", new TableInfo.Column("timestamp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("recordType", new TableInfo.Column("recordType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("individualId", new TableInfo.Column("individualId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRecords.put("sourceRecordId", new TableInfo.Column("sourceRecordId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRecords = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysRecords.add(new TableInfo.ForeignKey("individuals", "SET NULL", "NO ACTION", Arrays.asList("individualId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesRecords = new HashSet<TableInfo.Index>(1);
        _indicesRecords.add(new TableInfo.Index("index_records_individualId", false, Arrays.asList("individualId"), Arrays.asList("ASC")));
        final TableInfo _infoRecords = new TableInfo("records", _columnsRecords, _foreignKeysRecords, _indicesRecords);
        final TableInfo _existingRecords = TableInfo.read(db, "records");
        if (!_infoRecords.equals(_existingRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "records(net.madscientists.geep.core.database.entity.RecordEntity).\n"
                  + " Expected:\n" + _infoRecords + "\n"
                  + " Found:\n" + _existingRecords);
        }
        final HashMap<String, TableInfo.Column> _columnsObservations = new HashMap<String, TableInfo.Column>(3);
        _columnsObservations.put("recordId", new TableInfo.Column("recordId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsObservations.put("observedAt", new TableInfo.Column("observedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsObservations.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysObservations = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysObservations.add(new TableInfo.ForeignKey("records", "CASCADE", "NO ACTION", Arrays.asList("recordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesObservations = new HashSet<TableInfo.Index>(1);
        _indicesObservations.add(new TableInfo.Index("index_observations_recordId", false, Arrays.asList("recordId"), Arrays.asList("ASC")));
        final TableInfo _infoObservations = new TableInfo("observations", _columnsObservations, _foreignKeysObservations, _indicesObservations);
        final TableInfo _existingObservations = TableInfo.read(db, "observations");
        if (!_infoObservations.equals(_existingObservations)) {
          return new RoomOpenHelper.ValidationResult(false, "observations(net.madscientists.geep.core.database.entity.ObservationEntity).\n"
                  + " Expected:\n" + _infoObservations + "\n"
                  + " Found:\n" + _existingObservations);
        }
        final HashMap<String, TableInfo.Column> _columnsInterventions = new HashMap<String, TableInfo.Column>(3);
        _columnsInterventions.put("recordId", new TableInfo.Column("recordId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("performedAt", new TableInfo.Column("performedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInterventions.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInterventions = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysInterventions.add(new TableInfo.ForeignKey("records", "CASCADE", "NO ACTION", Arrays.asList("recordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesInterventions = new HashSet<TableInfo.Index>(1);
        _indicesInterventions.add(new TableInfo.Index("index_interventions_recordId", false, Arrays.asList("recordId"), Arrays.asList("ASC")));
        final TableInfo _infoInterventions = new TableInfo("interventions", _columnsInterventions, _foreignKeysInterventions, _indicesInterventions);
        final TableInfo _existingInterventions = TableInfo.read(db, "interventions");
        if (!_infoInterventions.equals(_existingInterventions)) {
          return new RoomOpenHelper.ValidationResult(false, "interventions(net.madscientists.geep.core.database.entity.InterventionEntity).\n"
                  + " Expected:\n" + _infoInterventions + "\n"
                  + " Found:\n" + _existingInterventions);
        }
        final HashMap<String, TableInfo.Column> _columnsFutureEvents = new HashMap<String, TableInfo.Column>(1);
        _columnsFutureEvents.put("recordId", new TableInfo.Column("recordId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFutureEvents = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysFutureEvents.add(new TableInfo.ForeignKey("records", "CASCADE", "NO ACTION", Arrays.asList("recordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesFutureEvents = new HashSet<TableInfo.Index>(1);
        _indicesFutureEvents.add(new TableInfo.Index("index_future_events_recordId", false, Arrays.asList("recordId"), Arrays.asList("ASC")));
        final TableInfo _infoFutureEvents = new TableInfo("future_events", _columnsFutureEvents, _foreignKeysFutureEvents, _indicesFutureEvents);
        final TableInfo _existingFutureEvents = TableInfo.read(db, "future_events");
        if (!_infoFutureEvents.equals(_existingFutureEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "future_events(net.madscientists.geep.core.database.entity.FutureEventEntity).\n"
                  + " Expected:\n" + _infoFutureEvents + "\n"
                  + " Found:\n" + _existingFutureEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsPredictedEvents = new HashMap<String, TableInfo.Column>(5);
        _columnsPredictedEvents.put("futureEventId", new TableInfo.Column("futureEventId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPredictedEvents.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPredictedEvents.put("earliestDate", new TableInfo.Column("earliestDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPredictedEvents.put("latestDate", new TableInfo.Column("latestDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPredictedEvents.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPredictedEvents = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPredictedEvents.add(new TableInfo.ForeignKey("future_events", "CASCADE", "NO ACTION", Arrays.asList("futureEventId"), Arrays.asList("recordId")));
        final HashSet<TableInfo.Index> _indicesPredictedEvents = new HashSet<TableInfo.Index>(1);
        _indicesPredictedEvents.add(new TableInfo.Index("index_predicted_events_futureEventId", false, Arrays.asList("futureEventId"), Arrays.asList("ASC")));
        final TableInfo _infoPredictedEvents = new TableInfo("predicted_events", _columnsPredictedEvents, _foreignKeysPredictedEvents, _indicesPredictedEvents);
        final TableInfo _existingPredictedEvents = TableInfo.read(db, "predicted_events");
        if (!_infoPredictedEvents.equals(_existingPredictedEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "predicted_events(net.madscientists.geep.core.database.entity.PredictedEventEntity).\n"
                  + " Expected:\n" + _infoPredictedEvents + "\n"
                  + " Found:\n" + _existingPredictedEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsPlannedTasks = new HashMap<String, TableInfo.Column>(5);
        _columnsPlannedTasks.put("futureEventId", new TableInfo.Column("futureEventId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlannedTasks.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlannedTasks.put("reminderDate", new TableInfo.Column("reminderDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlannedTasks.put("dueDate", new TableInfo.Column("dueDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlannedTasks.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlannedTasks = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPlannedTasks.add(new TableInfo.ForeignKey("future_events", "CASCADE", "NO ACTION", Arrays.asList("futureEventId"), Arrays.asList("recordId")));
        final HashSet<TableInfo.Index> _indicesPlannedTasks = new HashSet<TableInfo.Index>(1);
        _indicesPlannedTasks.add(new TableInfo.Index("index_planned_tasks_futureEventId", false, Arrays.asList("futureEventId"), Arrays.asList("ASC")));
        final TableInfo _infoPlannedTasks = new TableInfo("planned_tasks", _columnsPlannedTasks, _foreignKeysPlannedTasks, _indicesPlannedTasks);
        final TableInfo _existingPlannedTasks = TableInfo.read(db, "planned_tasks");
        if (!_infoPlannedTasks.equals(_existingPlannedTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "planned_tasks(net.madscientists.geep.core.database.entity.PlannedTaskEntity).\n"
                  + " Expected:\n" + _infoPlannedTasks + "\n"
                  + " Found:\n" + _existingPlannedTasks);
        }
        final HashMap<String, TableInfo.Column> _columnsWaitingDelays = new HashMap<String, TableInfo.Column>(5);
        _columnsWaitingDelays.put("futureEventId", new TableInfo.Column("futureEventId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaitingDelays.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaitingDelays.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaitingDelays.put("delayElapsedAt", new TableInfo.Column("delayElapsedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWaitingDelays.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWaitingDelays = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWaitingDelays.add(new TableInfo.ForeignKey("future_events", "CASCADE", "NO ACTION", Arrays.asList("futureEventId"), Arrays.asList("recordId")));
        final HashSet<TableInfo.Index> _indicesWaitingDelays = new HashSet<TableInfo.Index>(1);
        _indicesWaitingDelays.add(new TableInfo.Index("index_waiting_delays_futureEventId", false, Arrays.asList("futureEventId"), Arrays.asList("ASC")));
        final TableInfo _infoWaitingDelays = new TableInfo("waiting_delays", _columnsWaitingDelays, _foreignKeysWaitingDelays, _indicesWaitingDelays);
        final TableInfo _existingWaitingDelays = TableInfo.read(db, "waiting_delays");
        if (!_infoWaitingDelays.equals(_existingWaitingDelays)) {
          return new RoomOpenHelper.ValidationResult(false, "waiting_delays(net.madscientists.geep.core.database.entity.WaitingDelayEntity).\n"
                  + " Expected:\n" + _infoWaitingDelays + "\n"
                  + " Found:\n" + _existingWaitingDelays);
        }
        final HashMap<String, TableInfo.Column> _columnsAttachments = new HashMap<String, TableInfo.Column>(6);
        _columnsAttachments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttachments.put("recordId", new TableInfo.Column("recordId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
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
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "ee694efb61e3eb092f0a900633eaa110", "eb8645365b241ea2e8f59584c0175264");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "individuals","records","observations","interventions","future_events","predicted_events","planned_tasks","waiting_delays","attachments");
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
      _db.execSQL("DELETE FROM `observations`");
      _db.execSQL("DELETE FROM `interventions`");
      _db.execSQL("DELETE FROM `future_events`");
      _db.execSQL("DELETE FROM `predicted_events`");
      _db.execSQL("DELETE FROM `planned_tasks`");
      _db.execSQL("DELETE FROM `waiting_delays`");
      _db.execSQL("DELETE FROM `attachments`");
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
