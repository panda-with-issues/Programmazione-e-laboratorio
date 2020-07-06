;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname problema_6) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
;; compiler instruction
(set-tessellation-shift-step!)

(define build-tile           ; val: image
  (lambda (tile short-side)  ; tile: img, short-side: integer non-negative number
    (glue-tiles
     (glue-tiles
      (glue-tiles
       tile
       (shift-right (quarter-turn-right tile) (* 2 short-side))
       )
      (shift-down (shift-right tile short-side) short-side)
      )
     (shift-down (quarter-turn-left tile) (* 2 short-side))
     )
    ))

(define L-tessellation  ; val: image
  (lambda (short-side)  ; short-side: integer non-negative number
    ; base cases
    (if (= short-side 1)
         L-tile
         ; recursive step
          (build-tile
           (L-tessellation (/ short-side 2))
           (/ short-side 2)
           )
        )
    ))

;; test

(L-tessellation 1)
(L-tessellation 2)
(L-tessellation 4)
(L-tessellation 16)
