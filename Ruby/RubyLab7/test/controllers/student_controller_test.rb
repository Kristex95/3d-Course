require "test_helper"

class StudentControllerTest < ActionDispatch::IntegrationTest
  test "should get create" do
    get student_create_url
    assert_response :success
  end

  test "should get read" do
    get student_read_url
    assert_response :success
  end

  test "should get update" do
    get student_update_url
    assert_response :success
  end

  test "should get delete" do
    get student_delete_url
    assert_response :success
  end
end
