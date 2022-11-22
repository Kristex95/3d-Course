require "test_helper"

class BookControllerTest < ActionDispatch::IntegrationTest
  test "should get create" do
    get book_create_url
    assert_response :success
  end

  test "should get read" do
    get book_read_url
    assert_response :success
  end

  test "should get update" do
    get book_update_url
    assert_response :success
  end

  test "should get delete" do
    get book_delete_url
    assert_response :success
  end
end
