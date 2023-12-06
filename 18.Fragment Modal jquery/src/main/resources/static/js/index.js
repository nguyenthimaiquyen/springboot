$(document).ready(function () {
    toastr.options.timeOut = 2500; // 2.5s

    let deleteStudentId = -1;

    $.validator.addMethod("vietnamesePhone", function (value, element) {
        return this.optional(element) || /^0[0-9]{9}/i.test(value);
    }, "Số điện thoại phải là dãy 10 ký tự số bắt đầu bằng số 0");

    //validate
    const validator = $("#student-form").validate({
        onfocusout: false, //khi sự kiện này xảy ra thì không validate
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                maxlength: 255
            },
            "address": {
                required: true,
                maxlength: 255
            },
            "phone": {
                required: true,
                vietnamesePhone: true
            },
            "className": {
                required: true,
                maxlength: 255
            }
            // "dob": {
            //     pastDate: true,
            //     pastDateCustom: true
            // }
        },
        messages: {
            "name": {
                required: "Student's name is required",
                maxlength: "Student's name must be less than 255 characters"
            },
            "address": {
                required: "Address is required",
                maxlength: "Address must be less than 255 characters"
            },
            "phone": {
                required: "Phone is required",
                vietnamesePhone: "Số điện thoại phải là dãy 10 ký tự số bắt đầu bằng số 0"
            },
            "className": {
                required: "ClassName is required",
                maxlength: "ClassName must be less than 255 characters"
            }
            // "dob": {
            //     pastDate: "Student date must be a past date",
            //     pastDateCustom: "Student must be greater or equal than 18 years old"
            // }
        }
    });

    //mở modal tạo mới sinh viên
    $('.create-student-btn').click(function () {
        $('#student-modal #save-student-btn').attr("action-type", "CREATE");
        $('#student-modal').modal('show');
    });

    //open modal to update a student
    $('.update-student-modal-open').click(async function (event) {
        $('#student-form').trigger("reset");
        validator.resetForm();
        //call api lên java và lấy dữ liệu
        const updateStudentId = parseInt($(event.currentTarget).attr("student-id"));
        let student = null;
        await $.ajax({
            url: "/students/" + updateStudentId,
            type: "GET",
            success: function (data) {
                student = data;
            },
            error: function (err) {
                toastr.warning(data.responseJSON.error);
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });

        if (!student) {
            toastr.error("Đã có lỗi xảy ra, vui lòng thử lại!")
            return;
        }
        //đổ dữ liệu vào form
        $('#student-form #name').val(student.name);
        $('#student-form #address').val(student.address);
        $('#student-form #phone').val(student.phone);
        $('#student-form #className').val(student.className);

        $('#student-modal #save-student-btn').attr('action-type', "UPDATE");
        $('#student-modal #save-student-btn').attr("student-id", updateStudentId);
        $('#student-modal').modal("show");
    });

    //create or update student
    $('#save-student-btn').click(function (event) {
        //validate
        const isValidForm = $('#student-form').valid();
        if (!isValidForm) {
            return;
        }

        const actionType = $(event.currentTarget).attr("action-type");
        const studentId = $(event.currentTarget).attr("student-id");
        //lấy dữ liệu từ form
        const formData = $('#student-form').serializeArray();
        if (!formData || formData.length === 0) {
            return;
        }
        //chuyển dữ liệu từ dạng object sang json
        const requestBody = {};
        for (let i = 0; i < formData.length; i++) {
            requestBody[formData[i].name] = formData[i].value;
        }
        const method = actionType === "CREATE" ? "POST" : "PUT";
        if (method === "PUT") {
            requestBody["id"] = studentId;
        }
        //call api lên backend
        $.ajax({
            url: "/students",
            type: method,
            data: JSON.stringify(requestBody), //dữ liệu được gửi vào trong body của HTTP
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                toastr.success((method === "CREATE" ? "Create" : "Update") + "a new student successfully!");
                $(event.currentTarget).attr("action-type", "");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
        $("#student-modal #save-student-btn").attr("action-type", "");
        $('#student-modal #save-student-btn').attr("student-id", "");
        // $('#student-form').trigger("reset");
    });

    //open modal delete student
    $('.delete-student-btn').click(function (event) {
        deleteStudentId = parseInt($(event.currentTarget).attr("student-id"));
        $('#student-delete-modal').modal('show');
    });

    //delete student
    $('#delete-student-btn').click(function () {
        $.ajax({
            url: "/students/" + deleteStudentId,
            type: "DELETE",
            success: function (data) {
                toastr.success("Bạn đã xóa thành công thông tin sinh viên!");
                setTimeout(() => {
                    location.reload();
                }, 1000);
            },
            error: function (err) {
                toastr.warning("Đã có lỗi xảy ra, vui lòng thử lại!");
            }
        });
    });

    //reset form
    $('#student-modal').on('hidden.bs.modal', function () {
        $("#student-modal #save-student-btn").attr("action-type", "");
        $('#student-modal #save-student-btn').attr("student-id", "");
        $('#student-form').trigger("reset");
        $('#student-form input').removeClass("error");
        validator.resetForm();
    });

    // // close modal -> clear form + reset form, delete action-type attribute at submit button
    // $(".close-modal").click(() => {
    //     $("#student-modal #save-student-btn").attr("action-type", "");
    //     $("#student-modal #save-student-btn").attr("student-id", "");
    //     $('#student-form').trigger("reset");
    //     validator.resetForm();
    // });

});