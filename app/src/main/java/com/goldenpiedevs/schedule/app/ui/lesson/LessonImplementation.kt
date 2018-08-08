package com.goldenpiedevs.schedule.app.ui.lesson

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.goldenpiedevs.schedule.app.core.dao.timetable.DaoLessonModel
import com.goldenpiedevs.schedule.app.ui.base.BasePresenterImpl

class LessonImplementation : BasePresenterImpl<LessonView>(), LessonPresenter {
    companion object {
        const val LESSON_ID = "LESSON_ID"
    }

    private lateinit var daoLessonModel: DaoLessonModel

    override fun showLessonData(bundle: Bundle) {
        daoLessonModel = DaoLessonModel().getLesson(bundle.getInt(LESSON_ID))

        val room = daoLessonModel.rooms.first()
        val noteModel = daoLessonModel.noteModel

        with(view) {
            showLessonName(daoLessonModel.lessonFullName)
            showLessonTime(daoLessonModel.getTime())
            showLessonType(daoLessonModel.lessonType)
            showLessonTeachers(daoLessonModel.teachers)

            room?.let {
                showLessonRoom(it.roomName)

                if (ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED)
                    showLessonLocation(it.getGeoPoint())
            }

            noteModel?.let {
                showNoteText(it.note)
                showNotePhotos(it.photos)
            }

        }
    }

    override fun onNoteSave() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoteEditView() {
        if (daoLessonModel.hasNote) {

        } else {

        }
    }
}