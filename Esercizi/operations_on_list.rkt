;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname operations_on_list) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; implement operations on lists using only the five fundamental list operator: null, null?, car, cdr, cons

;; implement a procedure that returns a list's length

(define length*  ; val: integer non-negative number
  (lambda (lst)  ; lst: list
    (if (null? lst)
        0
        (+ 1 (length* (cdr lst)))
        )
        ))
;; test
(length* '())            ; 0
(length* '(1))           ; 1
(length* '(1 2 3 4 5 6)) ; 6

;; implement a procedure that returns the index of the first occurrence of a given element inside a list of numbers

(define index-of   ; val: integer non-negative number
  (lambda (i lst)  ; i: number, lst: list of numbers
    (if (= i (car lst))
        0
        (+ 1 (index-of i (cdr lst)))
        )
    ))

;; test

(index-of 4 '(4 3 2 1)) ; 0
(index-of 4 '(1 2 3 4)) ; 3
(index-of 4 '(2 4 4 2)) ; 1
