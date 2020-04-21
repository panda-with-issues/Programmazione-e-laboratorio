;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname manhattan) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
; Problem:
; How many paths join two points in a Manhattan grid, knowing that we want to exclude paths longer than necssary? (In other words, route-length < p_x + p_y)

(define manhattan-paths  ; val: integer non-negative number
  (lambda (x y)          ; same as val.
                         ; we are placing the grid's origin in our starting point, then x and y are the horizontal and vertical distance from target point
    ; base cases
    (if (or (= x 0) (= y 0))
        ; if starting and target point are horizontally or vertical aligned, there's only a way through not longer than x + y
        1
        ; recursive step
        (+
         (manhattan-paths (- x 1) y) ; I'm approaching horizontally
         (manhattan-paths x (- y 1)) ; I'm approaching vertically
         )
        )
    ))

;; test

(manhattan-paths 1 1) ; 2
(manhattan-paths 2 1) ; 3
(manhattan-paths 2 2) ; 6