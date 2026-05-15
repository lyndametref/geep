package net.madscientists.geep.core.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.lang.Override;
import java.lang.SuppressWarnings;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
final class GeepDatabase_AutoMigration_1_2_Impl extends Migration {
  public GeepDatabase_AutoMigration_1_2_Impl() {
    super(1, 2);
  }

  @Override
  public void migrate(@NonNull final SupportSQLiteDatabase db) {
    db.execSQL("ALTER TABLE `individuals` ADD COLUMN `notes` TEXT DEFAULT NULL");
  }
}
