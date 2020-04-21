;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname esame_27_1_2015) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define factors->number
  (lambda (f-ls e-ls)
    (if (null? f-ls)
        1
        (let ((f (car f-ls))
              (e (car e-ls))
              )
          (* (expt f e) (factors->number (cdr f-ls) (cdr e-ls)))
          ))))

(factors->number '(5) '(1)) ;5
(factors->number '(2 3) '(2 2)) ;36
(factors->number '(2) '(3)) ;8
(factors->number '(2 5) '(2 1)) ;20
(factors->number '(2 3 5) '(2 2 1)) ;180
(factors->number '(2 5 11) '(1 2 1)) ;550