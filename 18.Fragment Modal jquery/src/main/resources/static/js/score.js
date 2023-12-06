$(document).ready(function () {
    toastr.options.timeOut = 2500; // 2.5s

    $.validator.addMethod("pastDate", function (value, element) {
        if (this.optional(element)) {
            return true;
        }
        const dob = new Date(value);
        dob.setHours(0);
        dob.setMinutes(0);
        dob.setSeconds(0);
        dob.setMilliseconds(0);
        const today = new Date();
        today.setHours(0);
        today.setMinutes(0);
        today.setSeconds(0);
        today.setMilliseconds(0);
        return this.optional(element) || dob - today < 0 || dob === today;
    }, "Test date must be a past date");

    $.validator.addMethod("pastDateCustom", function (value, element) {
        if (this.optional(element)) {
            return true;
        }
        console.log(value)
        console.log(element)
        const testMonth = new Date(value).getMonth();
        const currentMonth = new Date().getMonth();
        return this.optional(element) || currentMonth - testMonth > 1;
    }, "Test date must be less than current date a month");

    const validator = $('#create-score-form').validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            'studentId': {
                required: true,
                maxlength: 100
            },
            'subjectId': {
                required: true,
                digits: true
            },
            "testDate": {
                required: true,
                pastDate: true,
                pastDateCustom: true
            },
            "score": {
                required: true,
                digits: true,
            }
        },
        messages: {
            'studentId': {
                required: "Student must be selected",
            },
            'subjectId': {
                required: "Subject must be selected",
            },
            "testDate": {
                required: "Test date is required",
                pastDate: "Test date must be a past date",
                pastDateCustom: "Test date must be less than current date a month"
            },
            "score": {
                required: "Score is required",
                digits: "Score must be digits"
            }
        }
    });

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
    $('#save-score-btn').click(async function () {
        const isValidForm = $('#create-score-form').valid();
        if (!isValidForm) {
            return;
        }
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
        await $.ajax({
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

    // // close modal -> clear form + reset form
    // $(".close-modal").click(() => {
    //     $('#create-score-form').trigger("reset");
    //     validator.resetForm();
    // });

    // reset form
    $('#score-creation-modal').on('hidden.bs.modal', function () {
        $('#create-score-form').trigger("reset");
        $('#create-score-form input').removeClass("error");
        validator.resetForm();
    });

});

