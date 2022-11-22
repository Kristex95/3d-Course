class CreateBooks < ActiveRecord::Migration[7.0]
  def change
    create_table :books do |t|
      t.string :name
      t.string :issue_date
      t.string :order_date
      t.belongs_to :student, null: false, foreign_key: true

      t.timestamps
    end
  end
end
