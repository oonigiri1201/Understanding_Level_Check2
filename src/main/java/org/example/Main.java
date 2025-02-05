package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//配列とリストまたはStreamAPIを使用して、
// 学生の名前と点数を管理するプログラムを作成してください。
// 名前の追加、削除、点数の更新、平均点の計算ができるようにしてください。
// 処理をループさせるにはWhile文を使って実現してください。

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    List<Student> studentsList = new ArrayList<>();
    int choiceNum = 0;

    while (true) {
      System.out.println("1.学生を追加");
      System.out.println("2.学生を削除");
      System.out.println("3.点数を更新");
      System.out.println("4.平均点を計算");
      System.out.println("5.全学生の情報を表示");
      System.out.println("6.終了");
      System.out.println("選択してください");

      try {
        choiceNum = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("無効な入力です。1〜６の数字を選択してください");
        scanner.nextLine();
        continue;
      }

      switch (choiceNum) {
        case 1://生徒の追加
          while (true) {
            try {
              System.out.println("追加する学生の名前を入力してください:");
              String name = scanner.nextLine();
              if (name.isEmpty()) {
                System.out.println("名前を入力してください(空欄を無効です)");
                continue;
              }

              System.out.println(name + "の点数を入力してください:");
              int score = scanner.nextInt();
              scanner.nextLine();

              Student student = new Student(name, score);
              studentsList.add(student);
              System.out.println(name + "さん、" + score + "点を追加しました");
              break;//正しい入力がされるまでループ。正しく入力されたら抜ける


            } catch (Exception e) {
              System.out.println("無効な入力です。最初からやり直してください。");
              scanner.nextLine();
            }
          }
          break;//１の処理が完了

        case 2://学生の削除
          //見つかったらtrueにするため一度falseをデフォルトにする
          boolean foundInCase2 = false;

          System.out.println("削除したい学生の名前を入力してください");
          String nameToRemove = scanner.nextLine();
          for (Student student : studentsList) {
            if (student.getName().equals(nameToRemove)) {
              System.out.println(nameToRemove + "さんを削除しました");
              foundInCase2 = true;
            }
            break;//削除した時点でループを抜ける
          }

          if (!foundInCase2) {
            System.out.println(
                nameToRemove + "さんは見つかりませんでした。最初から入力してください");
            System.out.println("登録生徒" + studentsList);
          }
          break;//２の処理を終了

        case 3://点数の更新
          System.out.println("登録生徒は以下の通りです");
          for (Student student : studentsList) {
            System.out.println(student.getName());
          }

          boolean foundCase3 = false;

          System.out.println("更新する生徒を入力してください");
          String upDateName = scanner.nextLine();

          for (Student student : studentsList) {  //studentListを検索し一致したあとも続くループ
            if (student.getName().equals(upDateName)) {
              System.out.println("新しい点数を入力してください");

              while (true) {
                try {
                  int newScore = scanner.nextInt();
                  scanner.nextLine();
                  student.setScore(newScore);
                  System.out.println(upDateName + "さんの点数を更新しました");
                  foundCase3 = true;
                  break;//点数が正常入力ループを抜ける
                } catch (Exception e) {
                  System.out.println("点数の入力が不正です。点数を入力してください");
                  scanner.nextLine();
                }
              }
              break;
            }
          }
          if (!foundCase3) {
            System.out.println(upDateName + "さんは登録されていません。最初から選択してください");
          }
          break;

        case 4://平均点の算出
          if (studentsList.isEmpty()) {
            System.out.println("登録されている生徒はいません");
          } else {
            double totalScore = 0;//点数の合計
            for (Student student : studentsList) {
              totalScore += student.getScore();
            }
            double average = totalScore / studentsList.size();
            System.out.println("平均点:" + average + "点");
          }
          break;//4の処理が終了

        case 5://全学生の表示
          if (studentsList.isEmpty()) {
            System.out.println("登録されている生徒はいません");
          } else {
            System.out.println("全学生を表示");
            for (Student student : studentsList) {
              System.out.println(student);
            }
          }
          break;//5の処理が終了

        case 6:
          System.out.println("プログラムを終了します");
          scanner.close();
          return;

        default:
          System.out.println("無効な入力です。１〜６を入力してください");
          break;
      }
    }
  }
}