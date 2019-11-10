;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname tiles) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
; Problem 1:
; We have a wall of dimension 12 * 1 and two tiles, the first with dimension 1 * 1 and the second woth dimension 1 * 2
; How many ways are there to tile the stripe?

(define tiling-a    ; val: integer non-negative number
  (lambda (length)  ; same as val
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
; Now the wall is 11 long and we have two tiles with dimension 1 * 1, one red and the other blue.
; We don't want any red tiles to be next each other. How many ways can we tile now?

(define tiling-b    ; integer non-negative number
  (lambda (length)  ; same as vale
    ; base cases
    (cond ((= length 1)
           ; i can use both a blue or a red
           2
           )
          ((= length 2)
           ; i can use 2 blue, or one blue and one red in any order
           3
           )
          (else
           ; recursive step
           (+
            (tiling-b (- length 1)) ; i'm using a blue tile
            (tiling-b (- length 2)) ; i'm using a red tile, so i'm forced to use a blue next
            )
           )
          )
    ))

;; test

(tiling-b 11) ; 233