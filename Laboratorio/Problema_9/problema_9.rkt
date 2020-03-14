;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_9) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Task 1

(define caesar-cipher  ; val: letter -> letter
  (lambda (key)        ; int in [0, 19]
    (lambda (char)     ; char
      (let ((char-val (char->integer char)))
        (integer->char (+ char-val key))
        )
      )
    ))