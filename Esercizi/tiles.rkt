;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname tiles) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
; Problem 1:
; We have a wall of dimension 12 * 1 and two tiles, the first with dimension 1 * 1 and the second woth dimension 1 * 2
; How many ways are there to tile the stripe?

(define tiling-a
  (lambda (length)
    ; base cases
    (cond ((= length 1)
           1
           )
          ((= length 2)
           2
           )
          (else
           ; recursive step
           (+
            (tiling-a (- length 1))
            (tiling-a (- length 2))
            )
           )
          )
    ))

;; test

(tiling-a 12) ; 233

; Problem 2:
; 