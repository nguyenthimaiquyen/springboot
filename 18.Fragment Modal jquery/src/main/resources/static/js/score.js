$(document).ready(function () {
    toastr.options.timeOut = 2500; // 2.5s

    //call api lấy thông tin của sinh viên
    $.ajax({
        url: "/scores/students",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            const studentSelection = $('#score-creation-modal #studentId');
            if (studentSelection.children().length === 0) {
                if (!data || data.length === 0) {
                    return;
                }
                let studentOptions = "";
                for (let i = 0; i < data.length; i++) {
                    studentOptions += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                }
                studentSelection.append($(studentOptions));
            }
        },
        error: function (data) {
            toastr.warning(data.responseJSON.error);
        }
    });

    //call api lấy thông tin của môn học
    $.ajax({
        url: "/scores/subjects",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            const subjectSelection = $('#score-creation-modal #subjectId');
            if (subjectSelection.children().length === 0) {
                if (!data || data.length === 0) {
                    return;
                }
                let subjectOptions = "";
                for (let i = 0; i < data.length; i++) {
                    subjectOptions += "<option value='" + data[i].id + "'>" + data[i].subjectName + "</option>";
                }
                subjectSelection.append($(subjectOptions));
            }
        },
        error: function (data) {
            toastr.warning(data.responseJSON.error);
        }
    });

    //open modal to create scores
    $('.create-score-btn').click(function () {
        $('#score-creation-modal').modal('show');
    });

    //create score for a student
    $('#save-score-btn').click(function () {
        //lấy dữ liệu từ form
        const formScoreData = $('#create-score-form').serializeArray();
        if (!formScoreData || formScoreData.length === 0) {
            return;
        }
        console.log(formScoreData)
        //chuyển dữ liệu từ object sang json
        const scoreRequestBody = {};
        for (let i = 0; i < formScoreData.length; i++) {
            scoreRequestBody[formScoreData[i].name] = formScoreData[i].value;
        }
        console.log(scoreRequestBody)
        //call api lên backend
        $.ajax({
            url: "/scores",
            type: "POST",
            data: JSON.stringify(scoreRequestBody),
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success("Create score for a student successfully!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (error) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });


});